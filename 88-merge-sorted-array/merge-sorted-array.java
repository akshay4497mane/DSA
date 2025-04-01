class Solution {
    /**
     * Problem: Merge two sorted arrays nums1 and nums2 into one sorted array.
     * nums1 has enough space to accommodate all elements from nums2.
     *
     * Approach: Use a two-pointer approach, starting from the end of both arrays. 
     * Compare elements from both arrays and place the larger element at the 
     * end of nums1.
     *
     * Time Complexity: O(m + n) where m is the length of nums1 and n is the length of nums2.
     * Space Complexity: O(1) as the merging is done in-place.
     */

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Pointers for the end of valid elements in nums1, nums2, and the merged array
        int i1 = m - 1;  // Pointer for the last valid element in nums1
        int i2 = n - 1;  // Pointer for the last element in nums2
        int ansi = m + n - 1;  // Pointer for the last position in nums1

        // Merge arrays from the end (largest to smallest)
        while (i1 >= 0 && i2 >= 0) {
            // Compare elements and place the larger one at the ansi position in nums1
            if (nums1[i1] > nums2[i2]) {
                nums1[ansi--] = nums1[i1--];  // Place from nums1
            } else {
                nums1[ansi--] = nums2[i2--];  // Place from nums2
            }
        }

        // If there are any remaining elements in nums2, copy them to nums1
        while (i2 >= 0) {
            nums1[ansi--] = nums2[i2--];  // Place remaining nums2 elements
        }
        // No need to handle remaining nums1 elements as they are already in place
    }
}
