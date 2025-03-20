// Problem: Given an unsorted integer array, find the smallest missing positive integer.
// Example: Input: [3,4,-1,1] -> Output: 2 | Input: [1,2,0] -> Output: 3

// Time Complexity: O(n) - Single pass to mark seen numbers, single pass to find missing.
// Space Complexity: O(n) - Extra boolean array of size n+1.

class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length; // Grab array length once
        
        // Create boolean array to track seen positives from 1 to n
        // Size n+1 because we’re checking up to n+1 as potential answer
        boolean[] seen = new boolean[n + 1];
        
        // Loop through nums to mark positives in range [1, n]
        for (int num : nums) {
            // Only care about positives <= n, ignore negatives, zeros, and big numbers
            if (num > 0 && num <= n) {
                seen[num] = true; // Mark this number as seen
            }
        }
        
        // Scan from 1 to n for first missing positive
        for (int i = 1; i < n + 1; i++) {
            // If i isn’t marked seen, it’s the smallest missing positive
            if (seen[i] == false) {
                return i; // Return it and bail
            }
        }
        
        // If all 1 to n are present, next positive (n+1) is the answer
        return n + 1;
    }
}