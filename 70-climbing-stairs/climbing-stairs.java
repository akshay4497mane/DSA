class Solution {
    /*
        n=1  ans: 1
        n=2 ans: 2(11,2)
        n=3 ans: 3 ways(111, 12, 21)
        n=4 ans: 5 ways(111 1, 12 1, 21 1, 11 2, 2 2)
        ways(n) = ways(n-1) + ways(n-2)
        Similar to Fibonacci
     */
    /*
    //Recursion  Time: O( 2^N ) | N=45 : Time Limit Exceeded
    public int climbStairs(int n) {
        if(n==1) return 1;
        if(n==2) return 2;
        return climbStairs(n-1) + climbStairs(n-2);
    }
    */

    //Top Down DP | 
    public int climbStairs(int n) {
        if(n==1) return 1;
        if(n==2) return 2;
        int[] waysDP = new int[n+1];
        for(int i=0;i<=n;i++)
            waysDP[i] = -1;
        return ways(n, waysDP);
    }
    public int ways(int n, int[] waysDP){
        if(n==1 || n==2) return n;
        if(waysDP[n] !=-1) return waysDP[n];
        waysDP[n] = ways(n-1, waysDP) + ways(n-2, waysDP);
        return waysDP[n];
    }
}