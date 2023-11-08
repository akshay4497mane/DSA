class Solution {
    /*
    // Recursive Fibonacci Time: O(2^N). Space: O(N)
        //Runtime 6ms | Beats 48.21%of users with Java
        //Memory 38.86MB | Beats 79.20%of users with Java
    public int fib(int n) {
        if(n<2) return n;
        return fib(n-1) + fib(n-2);
    }
    */

    /*
    //DP Top Down| Recursion + Memoization =>  Time: O(N), Space: O(N + N)
    //Runtime 0ms | Beats 100.00%of users with Java
    //Memory | 39.02MB | Beats 53.65%of users with Java
    public int fib(int n) {
        int[] DP = new int[n+1];
        for(int i=0; i<DP.length; i++)
            DP[i] = -1;
        return fibHelper(n , DP);
    }
    public int fibHelper(int n, int[] dp){
        if(n<2) return n;
        if(dp[n] != -1)
            return dp[n];
        else
            dp[n] = fibHelper(n-1, dp) + fibHelper(n-2, dp);
        return dp[n];
    }
    */

    /*
    //DP Bottom Up | Tabulation O(N) space
        //Runtime 0ms | Beats 100.00%of users with Java
        //Memory 39.29MB | Beats 31.17%of users with Java
    public int fib(int n) {
        if(n<2) return n;
        int[] DP = new int[n+1];
        DP[0] = 0;
        DP[1] = 1;
        for(int i=2; i<=n; i++)
            DP[i] = DP[i-1] + DP[i-2];
        return DP[n];
    }
    */
    //DP Bottom Up | Tabulation O(N) space
    public int fib(int n) {
        if(n<2) return n;
        int a = 0;
        int b = 1;
        int c = a+b;
        for(int i=2; i<=n; i++){
            c = a+b;
            a = b;
            b = c;
        }
        return c;
    }
}