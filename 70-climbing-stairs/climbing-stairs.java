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
    public int climbStairs_2(int n) {
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
    public int climbStairs(int n) {
        int[] dp = new int[n + 2];

        dp[n] = 1;      // base case
        dp[n + 1] = 0;  // safe padding

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = dp[i + 1] + dp[i + 2];
        }
        return dp[0];
    }
/*
Good question. This transition is what interviewers actually test.

Think in 4 mechanical steps. Don’t overthink it.

Identify the DP state from recursion
From your memoized code:

dfsMemo(i) = number of ways to reach step N starting from step i

So:
dp[i] = number of ways to reach N from i

That’s already your memo array. Bottom-up just fills it in reverse order.

Extract base cases and make them table entries
From recursion:

if (i == N) return 1
if (i > N) return 0

Translate to DP table:
dp[N] = 1
dp[N+1] = 0 (implicit, we don’t even need to store it)

Convert the recurrence literally
Recursive relation:

dfsMemo(i) = dfsMemo(i+1) + dfsMemo(i+2)

Bottom-up equivalent:

dp[i] = dp[i+1] + dp[i+2]

No change. Same formula. Only order of computation changes.

Decide iteration order (this is the key intuition)
Recursive version:

i depends on i+1 and i+2

So those must already be computed

Therefore:

Loop from right to left

i = N-1 → 0

Bottom-up DP (direct translation):
*/
}