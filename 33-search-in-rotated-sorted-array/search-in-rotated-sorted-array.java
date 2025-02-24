class Solution {
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length-1, mid=0;
        while(low <= high){
            mid = low + (high-low)/2;
            if( nums[mid]==target )
                return mid;
            //identify sorted half
            if( nums[low] <= nums[mid] ){//left half is sorted
                if( nums[low]<= target && target <= nums[mid]){
                    high = mid-1;
                }else{
                    low = mid+1;
                }
            }else{ //right half is sorted
                if( nums[mid] <= target && target <= nums[high]){
                    low = mid+1;
                }else{
                    high = mid-1;
                }
            }
        }
        return -1;
    }
}