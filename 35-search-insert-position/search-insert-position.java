class Solution {
    public int searchInsert(int[] nums, int target) {
    /*
    APPROACH 1 :
            linear search, Time : O(N), Space : O(1)
    APPROACH 2 :
        Binary Search, Time( Log N)
    */
        int N = nums.length;
        int start = 0, end = N-1, mid = start + (end-start)/2; 
        //int mid = (low + high) / 2;   May cause overflow
        while( start <= end ){
            mid = start + (end-start)/2;
            if( target == nums[mid]){
                return mid;
            }else if( target < nums[mid] ){
                end = mid - 1;
            }else if( target > nums[mid]){
                start = mid + 1;
            }
        }
        return start;
    }
}