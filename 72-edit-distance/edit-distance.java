class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=0; i<m;i++){
            dp[i][n] = m-i;
        }
        for(int j=0; j<n; j++){
            dp[m][j] = n-j;
        }
        for(int i=m-1;i>=0;i--){
            for(int j=n-1; j>=0; j--){
                if(word1.charAt(i)==word2.charAt(j)){
                    dp[i][j] = 0 + dp[i+1][j+1];
                }else{
                    dp[i][j] = 1 + Math.min(Math.min( dp[i+1][j],// delete
                                            dp[i][j+1]), //insert
                                            dp[i+1][j+1]);//replace
                }
            }
        }

        for(int i=0;i<m+1;i++){
            System.out.println("");
            for(int j=0; j<n+1; j++){
                System.out.print(dp[i][j] + " ");
            }
        }
        return dp[0][0];
    }
}
/*
dp[i][j] = 
  r o s ""
h       5
o       4
r       3
s       2
e       1
""3 2 1 0
*/