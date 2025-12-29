class Solution {
    /* DP
    Using 2D DP
    // dp(i, j) = min(dp(i−1, j), dp(i−1, j−1), dp(i, j−1)) + 1
    */
    public int maximalSquare_2DDP(char[][] A) {
        int M = A.length, N=A[0].length, ans = Integer.MIN_VALUE;
        int[][] DP = new int[M][N];
        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                if( A[i][j] == '1' ){ //Dont do anything if '0'
                    if( i==0 || j==0 )
                        DP[i][j] = 1;
                    else
                        DP[i][j] = 1 + Math.min(DP[i-1][j], Math.min(DP[i][j-1], DP[i-1][j-1]));
                    ans = Math.max(ans, DP[i][j]);
                }
            }    
        }
        return ans * ans; //Output is SQUARE of ANS
    }
    //Using 1 D DP
    public int maximalSquare(char[][] A) {
        int m = A.length, n = A[0].length, ans = 0; // DONT USE Integer.MIN_VALUE;
        int[] DP = new int[n];
        for(int i=0; i<m; i++){
            int prev = 0; //DP[i-1][j-1];
            for(int j=0; j<n; j++){
                int temp = DP[j];
                if(A[i][j]=='1'){
                    if( j==0 )//DONT use i==0
                        DP[j] = 1;
                    else
                        DP[j] = 1+Math.min( DP[j-1], Math.min( DP[j], prev )); //DONT Forget +1
                    ans = Math.max( ans, DP[j] );
                }else
                    DP[j] = 0;
                prev = temp;
            }
        }
        return ans * ans;
    }
}