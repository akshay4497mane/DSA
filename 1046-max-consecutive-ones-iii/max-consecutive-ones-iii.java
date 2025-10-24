class Solution {
    public int longestOnes(int[] nums, int k) {
        int left=0, right=0; //sliding window
        int numZeroes=0;
        int ans=0;
        while(right<nums.length){
            if( nums[right]==0 ){
                numZeroes++;
            }
            while(numZeroes > k){
                if( nums[left]==0 )
                    numZeroes--;
                left++;
            }
            ans = Math.max(ans, right-left+1);
            right++;
        }
        return ans;
    }
}