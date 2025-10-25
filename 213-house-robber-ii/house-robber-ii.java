class Solution {
    public int rob(int[] nums) {
        int n=nums.length;
        if(n==1) return nums[0];
        int ansWithoutLast = robHelper(nums,0,n-2);
        int ansWithoutFirst = robHelper(nums,1,n-1);
        return Math.max(ansWithoutFirst, ansWithoutLast);
    }
    private int robHelper(int[] nums, int start, int end){
        int rob1=0, rob2=0;
        for(int i=start; i<=end; i++){
            int newRob = Math.max(rob1 + nums[i], rob2);
            rob1 = rob2;
            rob2 = newRob;
        }
        return rob2;
    }
}