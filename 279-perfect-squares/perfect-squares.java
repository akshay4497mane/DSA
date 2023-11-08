class Solution {
/*
N=6, 111111, 211 => ans =3
N=10, 1=> 10, 2111111 => 6,2211 => 4, 31 => ans =2
N=9 : 3 => ans 1
N=12, 3111 => 4, 222 => ans =3 | greedy fails 
f(N) = 1 + min(f(N-1^2), f(N-2^2), f(N-3^2).... )
f(12) = 1+ min(f(11), f(8), f(3) )
*/

    /*
    //DP Top Down Recursive | Time: O( N Sqrt(N) ) | Space: O( N )
        //Runtime 69ms | Beats 26.55%of users with Java
        //Memory 41.31MB | Beats 90.64%of users with Java
    public int numSquares(int n) {
        if(n==0) return 0; // or if(n<=2) return n;
        int[] dp = new int[n+1];
        for(int i=0;i<=n;i++)
            dp[i] = -1;
        return helper(n, dp);
    }
    public int helper(int n, int[] dp){
        if(n==0) return 0; // or if(n<=2) return n;
        if(dp[n] != -1)
            return dp[n];
        int tempAns = Integer.MAX_VALUE;
        for(int x=1; x<=Math.sqrt(n); x++){
            tempAns = Math.min( tempAns, helper(n-x*x, dp) );
        }
        dp[n] = 1 + tempAns;
        return dp[n];
    }
    */
    //DP Bottom UP iterative | Time: O(N Sqrt(N)) | Space: O(N)
    public int numSquares(int n) {
        if(n==0) return 0; // or if(n<=2) return n;
        int[] dp = new int[n+1];

        dp[0] = 0;
        int tempAns =0;
        for( int i=1; i<=n; i++ ){
            tempAns =Integer.MAX_VALUE;
            for(int x=1; x*x <= i ; x++){
                tempAns = Math.min(tempAns , dp[i-x*x] );
            }
            dp[i] = 1+tempAns;
        }
        return dp[n];
    }
}