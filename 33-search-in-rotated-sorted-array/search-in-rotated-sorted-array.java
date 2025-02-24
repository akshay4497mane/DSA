/*
Problem Title: Search in Rotated Sorted Array
Approach(very short): Use binary search to identify the sorted half and adjust search space accordingly.
Time: O(log N) - Binary search reduces search space by half in each iteration.
Space complexity: O(1) - No extra space used.
Related : 
Part 2: Search Rotated Sorted array (DUPLICATE Elements)
https://www.youtube.com/watch?v=w2G2W8l__pc


*/

class Solution {
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
