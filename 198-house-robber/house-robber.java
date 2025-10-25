class Solution {
/*
Recurrence :  rob[n] = max( arr[n] + rob[n-2], rob[n-1] ). 
ARR   : 1 2 3 1
Cache : 1 2 4 4    
*/
    public int rob(int[] nums) {
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
}