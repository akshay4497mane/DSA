class Solution {
    public int search(int[] nums, int target) {
        //nums = [4,5,6,7,0,1,2], target = 0
        int l = 0, r=nums.length-1, mid = l+(r-l)/2;
        while(l<=r){
            mid = l+(r-l)/2; //0+6/2=3
            if(nums[mid]==target) return mid;
            if(nums[l] <= nums[mid]){ // left half is sorted
                if( target>=nums[l] && target<=nums[mid] ){
                    r = mid-1;
                }else{
                    l = mid+1;
                }
            }else{ //right half is sorted
                if( target>=nums[mid] && target<=nums[r] ){
                    l = mid+1;
                }else{
                    r = mid-1;
                }                
            }
        }
        return -1;
    }
}

















/*
Problem Title: Search in Rotated Sorted Array
Approach(very short): Use binary search to identify the sorted half and adjust search space accordingly.
Time: O(log N) - Binary search reduces search space by half in each iteration.
Space complexity: O(1) - No extra space used.
Related : 
Part 2: Search Rotated Sorted array (DUPLICATE Elements)
https://www.youtube.com/watch?v=w2G2W8l__pc


*/

class Solution1 {
    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1, mid = 0;
        
        while (low <= high) {
            mid = low + (high - low) / 2; // Calculate mid to avoid overflow
            if (nums[mid] == target) // If mid element is the target, return index
                return mid;

            // Identify the sorted half
            if (nums[low] <= nums[mid]) { // Left half is sorted
                // Check if the target lies within the sorted left half
                if (nums[low] <= target && target <= nums[mid]) {
                    high = mid - 1; // Search in the left half
                } else {
                    low = mid + 1; // Search in the right half
                }
            } else { // Right half is sorted
                // Check if the target lies within the sorted right half
                if (nums[mid] <= target && target <= nums[high]) {
                    low = mid + 1; // Search in the right half
                } else {
                    high = mid - 1; // Search in the left half
                }
            }
        }
        
        return -1; // Target not found
    }
}
