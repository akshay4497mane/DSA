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
    public boolean wordBreak(String s, List<String> wordDict) {
        Boolean[] memo = new Boolean[s.length()+1];
        Set<String> wordSet = new HashSet<>(wordDict); 
        return dfs(0, s, wordSet, memo);
    }
    boolean dfs(int i, String s, Set<String> wordSet, Boolean[] memo){
        if(i == s.length()) return memo[i]=true;
        if(memo[i] != null) return memo[i];
        for(int j=i+1; j<s.length()+1; j++){
            //System.out.println(i + " " + j + " " + s.substring(i,j) + " " + wordSet.contains(s.substring(i,j)));
            if(wordSet.contains(s.substring(i,j)) && dfs(j, s, wordSet, memo))
                return memo[i] = true;
        }
        return memo[i] = false;
    }
}