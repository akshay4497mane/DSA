import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {

    /*
     * Complete the 'countGroups' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING_ARRAY related as parameter.
     */

    public static int countGroups(List<String> related) {
        int N = related.size();
        int ans = N;
        for(int i=0; i<N ; i++){
            for(int j=0;j<N;j++){
                if(i==j)
                    continue;
                if(related.get(i).charAt(j) == '1' ){
                    if(i<j){
                        ans--;
                    }else if( i>j && related.get(j).charAt(i) !='1'){
                        ans--;
                    }
                    if(ans == 1)
                        return ans;
                }
                    
            }
        }
        return ans;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int relatedCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> related = IntStream.range(0, relatedCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        int result = Result.countGroups(related);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
