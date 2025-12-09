class Solution {
    public int longestPalindromeSubseq(String s) {
        String revS = new StringBuilder(s).reverse().toString();
        return LCS(s, revS);
    }
    int LCS(String s1, String s2){
        int n1 = s1.length(), n2 = s2.length();
        int[][] DP = new int[n1+1][n2+1];
        for(int i=n1-1; i>=0; i--){
            for(int j=n2-1; j>=0; j--){
                if(s1.charAt(i) == s2.charAt(j)){
                    DP[i][j] = 1 + DP[i+1][j+1];
                }else{
                    DP[i][j] = Math.max(DP[i+1][j], DP[i][j+1]);
                }
            }
        }
        return DP[0][0];
    }
}
