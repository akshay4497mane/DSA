class Solution {
    public int maxSubArray(int[] nums) {
        int N = nums.length, summ = 0, maxSum = Integer.MIN_VALUE;
        
        for(int i = 0; i<N ;i++){
            summ += nums[i];
            summ = Math.max( summ, nums[i] );
            maxSum = Math.max( summ, maxSum);
        }
        return maxSum;
    }
}