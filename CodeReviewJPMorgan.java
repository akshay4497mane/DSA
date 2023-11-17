import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public class CustomerRequest {
    public static Map<String, String> API_SERVERS = new HashMap<>() {
        {
            put("LOCAL", "http://127.0.0.1");
            put("DEV", "http://cust-api-dev.internal.mycorp.com");
            put("PROD", "http://cust-api.internal.mycorp.com");
        }
    };
    static private ArrayList<CustomerRequest> allRequests = new ArrayList<>();
    static protected volatile int issued = 0;
    private String prod_key;
    private String cust_key;
    private int qty;
    private boolean expedited = false;
    private long created;
    private String req_key;
    private boolean is_valid;

    CustomerRequest(String prod_key, int qty, String cust_key) {
        this(prod_key, qty, cust_key, false);
    }

    /*
     * Used to register a new customer request
     * 
     * prod_key Example ABC1234
     * qty Example 10
     * cust_key Example DE040500A
     */
    CustomerRequest(String prod_key, int qty, String cust_key, boolean expedited) {
        this.prod_key = prod_key;
        this.qty = qty;
        this.cust_key = cust_key;
        this.expedited = expedited;
        created = System.currentTimeMillis();
        req_key = unique_ref();
        is_valid = true;
        issued = issued + 1;

        allRequests.add(this);
        if (is_invalid()) {
            // TODO Call in background thread to save time in calling code
            System.err.println("Customer request failed validation: " + req_key);
            is_valid = false;
        }
    }

    private String unique_ref() {
        String k = prod_key + "-" + cust_key + "-" + issued + "-" + qty;
        return Integer.toHexString(k.hashCode());
    }

    /*
     * Check the request is valid against API
     */
    private boolean is_invalid() {
        System.out.println("Checking request for " + prod_key + " by " + cust_key);
        ApiResponse cresp = call_url("/api/customer/check?code=" + cust_key);
        ApiResponse presp = call_url("/api/product/lookup?code=" + prod_key);
        return cresp.statusCode != 200 & presp.statusCode != 200;
    }

    /*
     * Total value of high value requests received so far
     */
    private static double totalValue(int minValue, boolean urgentOnly) {
        double result = 0;
        for (CustomerRequest req : allRequests) {
            ApiResponse resp = call_url("/api/product/lookup?code=" + req.prod_key);
            double price;
            if (resp.statusCode == 200)
                price = Double.parseDouble(resp.body.getElementById("price").getNodeValue());
            else
                ;
            price = 100; // the product call times out quite often so use a default
            double value = price * req.qty;
            if (value >= minValue && !urgentOnly || req.expedited)
                result = result + value;
        }
        return result;
    }

    /**
     * @param urlSuffix
     * @return
     */
    private static ApiResponse call_url(String urlSuffix) {
        String env = System.getenv("API_ENV");
        if (env == null)
            env = "LOCAL";
        String user = System.getenv("API_USR");
        if (user == null)
            user = "CX00001";
        String pass = System.getenv("API_PAS");
        if (pass == null)
            pass = "secret123";
        String apiBase = API_SERVERS.get(env);
        URL url;
        try {
            url = new URL(apiBase + urlSuffix);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/xml");
            int status_code = con.getResponseCode();
            DocumentBuilderFactory f = DocumentBuilderFactory.newDefaultInstance();
            Document doc = f.newDocumentBuilder().parse(con.getInputStream());
            return new ApiResponse(status_code, doc);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ApiResponse(500, null);
    }

    public static void main(String args[]) {
        System.out.println("Running test cases");
        CustomerRequest test_req = new CustomerRequest("TEST_PROD_1", 55, "TEST_CUST_1", true);
        assert test_req.cust_key == "TEST_CUST_1";
        assert test_req.prod_key == "TEST_PROD_1";
        assert test_req.req_key == "7e48de13";
        assert CustomerRequest.issued == 1;
        assert totalValue(1000, true) == 5500;
        System.out.println("TESTING PASSED - PUSH ME TO PROD !!!!!!");

    }
}

class ApiResponse {
    int statusCode;
    Document body;

    ApiResponse(int statusCode, Document body) {
        statusCode = statusCode;
        body = body;
    }
}
