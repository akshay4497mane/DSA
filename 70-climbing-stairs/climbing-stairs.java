class Solution {
    //Approach 1 : Raw Recursion --> accumulate ways
    int noOfWays = 0, N;
    public int climbStairs_1(int n) {
        N=n;
        dfs( 1 );
        return noOfWays;
    }
    //cannot be memoized as return type is void
    void dfs( int i ){
        if(i==N){ noOfWays++; return; }
        dfs(i+1); 
        dfs(i+2);
    }

    //Approach 2 :
    public int climbStairs(int n) {
        N=n;
        memo = new int[N];
        return dfsMemo(0);
    }
    int[] memo;
    int dfsMemo(int i){
        if(i==N) return 1;
        if(i>N) return 0;
        if(memo[i] != 0) return memo[i];
        return memo[i] = dfsMemo(i+1) + dfsMemo(i+2);
    }
}