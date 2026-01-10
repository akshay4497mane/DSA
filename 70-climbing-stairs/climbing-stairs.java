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
        memo = new int[N+2];
        return dfsMemo(0);
    }
    int[] memo;
    int dfsMemo(int i){
        if(memo[i] != 0) return memo[i];
        if(i>N) return memo[i]=0;
        if(i==N) return memo[i]=1;
        return memo[i] = dfsMemo(i+1) + dfsMemo(i+2);
    }
/*
//dfsMemo(i) = number of ways to reach step N starting from step i
//dp[i] = number of ways to reach N from i
//Approach 3
[0 1 2]
n = 3
int[] DP;  [0, 0, 0, 0] | N=5 | dp[3] = 3->5 
//DP[i] = how many ways you can go from i -> N?
DP[3] = 1
DP[2] = 1
DP[1] = 2
DP[0] = 3

climbStairs(3) -> 3
climb(1) -> 1
*/
}