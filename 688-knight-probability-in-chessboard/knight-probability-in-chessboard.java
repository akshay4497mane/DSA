class Solution {
/*APPROACH 1 :
RECURSIVE SOLUTION
At every step, 8 recursive calls,
Explanation:
https://www.youtube.com/watch?v=lYywnLlArbU&t=529s&ab_channel=DeepCodes
*/
    double total = 0, success = 0;
    public double knightProbability_Approach1(int n, int k, int row, int column) {
        solve(n,k,row,column);
        total = Math.pow(8, k);
        return success/total;
    }
    void solve(int n, int k, int r, int c){
        if(r<0 || c <0 || r>=n || c>=n)return;
        if(k==0){success++; return;}
        solve(n, k-1, r-2 , c+1);
        solve(n, k-1, r-1 , c+2);
        solve(n, k-1, r+1 , c+2);
        solve(n, k-1, r+2 , c+1);

        solve(n, k-1, r-2 , c-1);
        solve(n, k-1, r-1 , c-2);
        solve(n, k-1, r+1 , c-2);
        solve(n, k-1, r+2 , c-1);
    }

/*
APPROACH 2 :
Use 3D DP, Memoization
Store Success/Total for every step instead of at last
At every step, 8 recursive calls,
Explanation:
https://www.youtube.com/watch?v=lYywnLlArbU&t=529s&ab_channel=DeepCodes

*/
    double[][][] dp;
    public double knightProbability_RecursiveDP(int n, int k, int row, int column) {
        dp = new double[n][n][k+1];
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
                for(int p=0;p< k+1;p++)
                    dp[i][j][p] = -1;
        return solve2(n,k,row,column);
    }
    double solve2(int n, int k, int r, int c){
        //System.out.println("solve( K " + k + "C: " + c + "R: " + r );
        if(r<0 || c <0 || r>=n || c>=n) return 0;
        if(k==0){ return 1; }
        if( dp[r][c][k] != -1 ) return dp[r][c][k];
        double r1 = solve2(n, k-1, r-2 , c+1);
        double r2 = solve2(n, k-1, r-1 , c+2);
        double r3 = solve2(n, k-1, r+1 , c+2);
        double r4 = solve2(n, k-1, r+2 , c+1);

        double r5 = solve2(n, k-1, r-2 , c-1);
        double r6 = solve2(n, k-1, r-1 , c-2);
        double r7 = solve2(n, k-1, r+1 , c-2);
        double r8 = solve2(n, k-1, r+2 , c-1);
        dp[r][c][k] = (r1+r2+r3+r4+r5+r6+r7+r8) / 8;
        //System.out.println( "r: " + r + "c: " + c + "k: " + k +"dp: " + dp[r][c][k] );
        return dp[r][c][k];
    }

/*
APPROACH 3 :
Bottom Up DP 
Explanation:Read Leetcode Editorial
*/
    public double knightProbability(int n, int k, int row, int column) {
        double[][][] DP = new double[k+1][n][n];
        DP[0][row][column] = 1.0;

        int[][] dir = {{1,2}, {1,-2}, {-1,2}, {-1,-2}, 
                        {2,1}, {2,-1},{-2,1}, {-2,-1} };

        for(int move = 1 ; move <= k ; move++){
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    for(int[] d : dir){
                        int prevI = i-d[0];
                        int prevJ = j-d[1];
                        if(prevI>=0 && prevI<n && prevJ>=0 && prevJ<n ){
                            DP[move][i][j] += DP[move-1][prevI][prevJ] / 8 ; 
                        }
                    }
                }   
            }
        }
        double ans = 0;
        for(int i=0; i<n ; i++){
            for(int j=0; j<n ; j++){
                ans += DP[k][i][j];
            }
        }
        return ans;
    }    
}