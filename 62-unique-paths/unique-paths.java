class Solution {
    int[][] DP =  new int[101][101];
    public int uniquePaths_Memoization(int m, int n) {
        if(m==0 || n==0) return 0;
        if(m==1 && n==1) return 1;
        if(DP[m][n] != 0) return DP[m][n];
        DP[m][n] = uniquePaths(m-1,n) + uniquePaths(m,n-1);
        return DP[m][n];
    }

    public int uniquePaths(int m, int n) {
        for(int i=0; i<n ; i++)
            DP[0][i] = 1;
        for(int i=0; i<m ; i++)
            DP[i][0] = 1;

        for(int i=1; i<m; i++){
            for(int j=1; j<n; j++){
                DP[i][j] = DP[i-1][j] + DP[i][j-1];    
            }
        }
        return DP[m-1][n-1];
    }
}