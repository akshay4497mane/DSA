/*
Problem Title: Search in Rotated Sorted Array with Duplicates
Approach(very short): Use binary search while handling duplicates by shrinking search space when low, mid, and high are equal.
Time: O(log N) in average case, O(N) in worst case (when many duplicates exist).
Space complexity: O(1) - No extra space used.
*/

/* Note: 
   - Similar to standard rotated array search but handles duplicates.
   - If nums[low] == nums[mid] == nums[high], we shrink the search space.
   - Otherwise, use binary search logic with sorted half identification.
*/

class Solution {
    public boolean search(int[] nums, int target) {
        int low = 0, high = nums.length - 1, mid = 0;
        
        while (low <= high) {
            mid = low + (high - low) / 2; // Calculate mid to avoid overflow
            
            if (nums[mid] == target) return true; // Target found

            // Handle duplicates: If low, mid, and high are same, shrink search space
            if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
                low++;
                high--;
                continue; // Skip duplicate elements
            }

            // Identify the sorted half
            if (nums[low] <= nums[mid]) { // Left half is sorted
                // Check if target lies in the sorted left half
                if (nums[low] <= target && target <= nums[mid]) {
                    high = mid - 1; // Search in the left half
                } else {
                    low = mid + 1; // Search in the right half
                }
            } else { // Right half is sorted
                // Check if target lies in the sorted right half
                if (nums[mid] <= target && target <= nums[high]) {
                    low = mid + 1; // Search in the right half
                } else {
                    high = mid - 1; // Search in the left half
                }
            }
        }
        
        return false; // Target not found
    }
}