class Solution {
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