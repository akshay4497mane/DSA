class Solution {
    //Approach : Use Two Pointer Approach
    public int[] twoSum(int[] nums, int target) {
        int left=0,right=nums.length -1;
        while(left <= right){
            System.out.println(nums[left] + " " + nums[right]);
           if(nums[left]+nums[right]==target){
             return new int[]{left+1,right+1};
           }
           if(nums[left]+nums[right] <target){
               left++;
           }else{
               right--;
           }
        }
        return new int[2];
    }
}