/*
Problem: Given a sorted array, find two numbers such that they add up to a given target.
Sample Input: nums = [2, 7, 11, 15], target = 9
Sample Output: [1, 2] (Since 2 + 7 = 9, return 1-based indices)

Approach: Use the Two Pointer approach, moving left or right based on sum comparison.
Time Complexity: O(N) (Single pass through array)
Space Complexity: O(1) (No extra space used)
*/

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int left = 0, right = nums.length - 1; // Initialize two pointers
        
        while (left <= right) { // Ensure pointers do not cross
            int sum = nums[left] + nums[right]; // Calculate sum of left and right elements
            
            if (sum == target) { // If sum matches target, return 1-based indices
                return new int[]{left + 1, right + 1};
            }
            
            if (sum < target) { // If sum is smaller, move left pointer right to increase sum
                left++;
            } else { // If sum is larger, move right pointer left to decrease sum
                right--;
            }
        }
        
        return new int[2]; // Return empty array if no solution is found
    }
}
