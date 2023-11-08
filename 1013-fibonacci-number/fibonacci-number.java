class Solution {
    /*
    // Recursive Time: O(2^N). Space: O()
    public int fib(int n) {
        if(n<2) return n;
        return fib(n-1) + fib(n-2);
    }
    //Runtime 6ms | Beats 48.21%of users with Java
    //Memory 38.86MB | Beats 79.20%of users with Java
    */
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
}