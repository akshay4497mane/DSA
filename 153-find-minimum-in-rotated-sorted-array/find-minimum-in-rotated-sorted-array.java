class Solution {
    public int findMin(int[] nums) {
        int low=0, high= nums.length-1, mid=0, ans = Integer.MAX_VALUE;
        while( low <= high){
            mid = low + (high-low)/2;
            //identify sorted half, find lowest value, store in ans, eliminate sorted half
            if( nums[low] <= nums[mid] ){ //LEFT half is sorted, find lowest, eliminate
                ans = Math.min( ans, nums[low] );
                low = mid + 1;
            }else{  //RIGHT half is sorted, find lowest, eliminate
                ans = Math.min( ans, nums[mid] );
                high = mid -1 ;
            }
        }
        return ans;
    }
}