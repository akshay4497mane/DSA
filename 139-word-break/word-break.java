class Solution {
/*
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
dfs(0) -> 
i=0: j=1-8|
j= 1 -> 2 -> 3
cat + dfs(3)
dfs(3) -> i=3|j=4-8
leetcode
dfs(0)-> 
j = 1 -> 
i=0, j4 -> leet + dfs(4)
*/
    //str : akshayymane 
    //akshay | akshayy | mane
    //Top Down DP
    public boolean wordBreak_topDown(String s, List<String> wordDict) {
        Boolean[] memo = new Boolean[s.length()+1];
        Set<String> wordSet = new HashSet<>(wordDict); 
        return dfs(0, s, wordSet, memo);
    }
    boolean dfs(int i, String s, Set<String> wordSet, Boolean[] memo){
        if(memo[i] != null) return memo[i];
        if(i == s.length()) return memo[i]=true;
        for(int j=i+1; j<s.length()+1; j++){
            //System.out.println(i + " " + j + " " + s.substring(i,j) + " " + wordSet.contains(s.substring(i,j)));
            if(wordSet.contains(s.substring(i,j)) && dfs(j, s, wordSet, memo))
                return memo[i] = true;
        }
        return memo[i] = false;
    }
    //Bottom Up DP
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] memo = new boolean[s.length()+1];
        //memo[i] means: substring s[i..n-1] is breakable
        memo[s.length()] = true;
        Set<String> wordSet = new HashSet<>(wordDict); 
        int start, end=s.length();
        while( end >= 0 ){
            for( start=end-1; start>=0; start-- ){
                if( wordSet.contains(s.substring(start, end)) && memo[end]==true){
                    memo[start] = true;
                    //end=start;  -> this is GREEDY choice, Not DP | DONT
                }
            }
            end--;
        }
        return memo[0];
    }

}