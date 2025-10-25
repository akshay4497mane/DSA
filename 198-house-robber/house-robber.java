class Solution {
/*
Recurrence :  rob[n] = max( arr[n] + rob[n-2], rob[n-1] ). 
ARR   : 1 2 3 1
Cache : 1 2 4 4    
*/

/*Approach 1 : Bottom Up DP | Filling DP Array from Left to Right |O(N) space/ O(N) Time 
Recurrence: dp[i] = max(nums[i] + dp[i-2], dp[i-1])
*/
    public int rob_1(int[] nums) {
        int n = nums.length;
        if(n==0) return 0;
        if(n==1) return nums[0];
        if(n==2) return Math.max(nums[0],nums[1]);
        int[] cache = new int[n];
        cache[0] = nums[0];
        cache[1] = Math.max(nums[0],nums[1]);
        for(int i=2;i<n; i++){
            cache[i] = Math.max( nums[i] + cache[i-2], cache[i-1] );
        }
        return cache[n-1];
    }

/*Approach 2 : Same as apprach 1, O(1) space */
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        int prev2 = nums[0];                       // dp[i-2]
        int prev1 = Math.max(nums[0], nums[1]);    // dp[i-1]

        for (int i = 2; i < n; i++) {
            int curr = Math.max(nums[i] + prev2, prev1);
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }
}