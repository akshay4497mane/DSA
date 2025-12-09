class Solution {
/*
i=0 | j =n-1 (last)
If s[i] == s[j], perform answer = 2 + LPS(i + 1, j - 1).
Else, perform answer = max(LPS(i, j - 1), LPS(i + 1, j).
*/
    String str;
    int[][] memo;
    public int longestPalindromeSubseq(String s) {
        str = s;
        memo = new int[s.length()][s.length()];
        return lps(0, s.length()-1);
    }
    int lps(int i, int j){
        if(i>j) return 0;
        if(i==j) return 1;
        if(memo[i][j]!=0) return memo[i][j];
        if(str.charAt(i) == str.charAt(j))
            return memo[i][j] = 2 + lps(i+1, j-1);
        else
            return memo[i][j] = Math.max(lps(i, j-1), lps(i+1,j));
    }

    //O(N^2)
    public int longestPalindromeSubseq_2(String s) {
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

/*
//Aditya
class Solution {
    public int longestPalindromeSubseq(String s) {
    int [][] dp = new int[s.length()][s.length()];
    for(int g=0;g<dp[0].length;g++){
            for(int i =0,j =g;j<dp.length;i++,j++){
                if(g==0){
                    dp[i][j]=1;
                }
               else if (g==1){
                    if(s.charAt(i)==s.charAt(j)){
                            dp[i][j]=2;
                    }else{
                          dp[i][j]=1; 
                    }
                }
                else{
                     if(s.charAt(i)==s.charAt(j)){
                            dp[i][j]=dp[i+1][j-1]+2;
                    }
                    else{
                        dp[i][j]= Math.max(dp[i+1][j],dp[i][j-1]);
                    }         
                }
            }
    }
    return dp[0][s.length()-1];
    }
}
  a b c
a 1 
b   1
c     1

*/