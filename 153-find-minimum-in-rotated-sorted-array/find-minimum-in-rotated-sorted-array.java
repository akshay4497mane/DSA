/*
Problem Title: Find Minimum in Rotated Sorted Array
Approach(very short): Use binary search to identify the sorted segment and update the minimum element accordingly.
Time: O(log n) on average (worst-case O(n) when the array is already sorted or nearly uniform)
Space complexity: O(1)
Additional Notes: 
  - Check if the current subarray is sorted; if yes, the smallest element is at the low index.
  - Adjust search boundaries by eliminating the sorted half.
*/

class Solution {
    public int findMin(int[] nums) {
        // Initialize pointers and the answer variable with the maximum possible integer value.
        int low = 0, high = nums.length - 1, mid = 0, ans = Integer.MAX_VALUE;
        
        // Perform binary search until the search space is exhausted.
        while (low <= high) {
            // Calculate mid to avoid overflow.
            mid = low + (high - low) / 2;
            
            // If the subarray from low to high is sorted, the smallest element is at the low index.
            if (nums[low] <= nums[high]) {
                ans = Math.min(ans, nums[low]);
                break; // Exit loop since the minimum is found.
            }
            
            // Identify which half is sorted.
            if (nums[low] <= nums[mid]) { // LEFT half is sorted.
                // The smallest element in the sorted left half is at index 'low'.
                ans = Math.min(ans, nums[low]);
                // Discard the left half by moving 'low' pointer beyond 'mid'.
                low = mid + 1;
            } else { // RIGHT half is sorted.
                // The smallest element in the sorted right half is at index 'mid'.
                ans = Math.min(ans, nums[mid]);
                // Discard the right half by moving 'high' pointer before 'mid'.
                high = mid - 1;
            }
        }
        
        // Return the smallest element found.
        return ans;
    }
}
