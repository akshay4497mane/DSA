class Solution {
/* 11/Nov/2025
*/
//Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
//                       L   R
//[0 0 0 0]
// R L
//k=0
    public int longestOnes(int[] nums, int k) {
        int l=0, r=0, count0=0, N=nums.length, ans=0;
        while(r<N){
            if(nums[r]==0){
                count0++;
            }

            while(count0>k && l<=r){
                if(nums[l]==0)
                    count0--;
                l++;
            }

            ans = Math.max(ans, r-l+1);
            r++;
        }
        return ans;
    }














    public int longestOnes_1(int[] nums, int k) {
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