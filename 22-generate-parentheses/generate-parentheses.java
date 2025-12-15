class Solution {
    List<String> ans = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        StringBuilder currAns = new StringBuilder(); // builds current parentheses string
        dfs(currAns, 0, 0, n); // start with zero open and close brackets
        return ans;
    }
    void dfs(StringBuilder currAns, int openCount, int closeCount, int n){
        // base case: used exactly n '(' and n ')'
        if(currAns.length() == 2*n){
            ans.add(currAns.toString()); // valid well-formed combination
            return; // stop exploring this path
        }
        // try adding '(' only if we still have remaining opens
        if(openCount < n){
            currAns.append('('); // choose '('
            dfs(currAns, openCount+1, closeCount, n); // pass incremented value, do NOT mutate current state
            currAns.deleteCharAt(currAns.length()-1); // backtrack to previous state
        }
        // try adding ')' only if it won’t make the string invalid
        if(closeCount < openCount){
            currAns.append(')'); // choose ')'
            dfs(currAns, openCount, closeCount+1, n); // again, no ++ to avoid state corruption
            currAns.deleteCharAt(currAns.length()-1); // backtrack
        }
    }
}