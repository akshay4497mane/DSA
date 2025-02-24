class Solution {
    public boolean search(int[] nums, int target) {
        int low=0, high = nums.length-1, mid=0;
        while( low <= high ){
            mid = low + (high-low)/2;
            if( nums[mid] == target ) return true;
            if( nums[low] == nums[mid] && nums[mid] == nums[high] ){
                low++;
                high--;
                continue;
            }
            //identify sorted half
            if( nums[low] <= nums[mid] ){ //LEFT half is sorted
                if( nums[low]<= target && target<= nums[mid] )
                    high = mid-1;
                else
                    low = mid+1;
            }else{ //RIGHT half is sorted
                if( nums[mid]<=target && target<=nums[high] )
                    low = mid+1;
                else
                    high = mid-1;
            }
        }
        return false;
    }
}