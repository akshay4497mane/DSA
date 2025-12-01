class Solution_1 {
    /**
Time Complexity : O(N * 2^N), where N is the length of string s
Hence, there could be 2^N possible substrings in the worst case. For each substring, it takes O(N) time to generate the substring and determine if it is a palindrome or not. This gives us a time complexity of O(N * 2^N))
*/
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        dfs(0, new ArrayList<String>(), result, s);
        return result;
    }
    void dfs(int start, List<String> currentList, List<List<String>> result,  String s) {
        if (start >= s.length()) 
            result.add(new ArrayList<String>(currentList));
        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                currentList.add(s.substring(start, end + 1));// add current substring in the currentList
                dfs(end + 1, currentList, result, s);
                currentList.remove(currentList.size() - 1);// backtrack and remove the current substring from currentList
            }
        }
    }
    boolean isPalindrome(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low++) != s.charAt(high--)) 
                return false;
        }
        return true;
    }
}

class Solution{
    private boolean[][] dp;          // constant reference
    private String s;                // constant reference
    private List<List<String>> res;  // constant reference

    public List<List<String>> partition(String str) {
        this.s = str;
        int n = s.length();
        this.dp = new boolean[n][n];
        this.res = new ArrayList<>();

        dfs(0, new ArrayList<>());
        return res;
    }

    void dfs(int start, List<String> curr) {
        if (start == s.length()) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(start, end)) {
                curr.add(s.substring(start, end + 1));
                dfs(end + 1, curr);
                curr.remove(curr.size() - 1);
            }
        }
    }

    boolean isPalindrome(int i, int j) {
        if (s.charAt(i) == s.charAt(j) &&
            (j - i <= 2 || dp[i + 1][j - 1])) {

            dp[i][j] = true;   // only state mutation
            return true;
        }
        return false;
    }
}