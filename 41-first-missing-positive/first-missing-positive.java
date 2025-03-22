// Problem: Given an unsorted integer array, find the smallest missing positive integer.
// Example: Input: [3,4,-1,1] -> Output: 2 | Input: [1,2,0] -> Output: 3


class Solution {
    // Approach 1 : Using boolean array of n size
    // Time Complexity: O(n) - Single pass to mark seen numbers, single pass to find missing.
    // Space Complexity: O(n) - Extra boolean array of size n+1.
    public int firstMissingPositive_booleanArray(int[] nums) {
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
    /* APPROACH 2 :
        1. Normalize the array by replacing invalid values (≤0 or >n) with 1 and check if 1 exists.  
        2. Use array indices as a hash to mark the presence of numbers.  
        3. Find the first missing positive by checking unmarked indices; return `n + 1` if all are present.
       Time Complexity: O(n) (Single pass modifications + One pass marking + One pass checking)
       Space Complexity: O(1) (In-place modification of the input array)
    */
    public int firstMissingPositive_approach2(int[] nums) {
        int n = nums.length;
        boolean seen1 = false;
        
        // Step 1: Normalize the array
        // Mark presence of 1, and replace out-of-range numbers with 1.
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) seen1 = true; // Check if 1 exists
            if (nums[i] <= 0 || nums[i] > n) nums[i] = 1; // Replace invalid values
        }
        
        // If 1 is not found, it is the smallest missing positive number
        if (!seen1) return 1;
        
        // Step 2: Use the array index as a hash to mark presence
        for (int i = 0; i < n; i++) {
            int index = Math.abs(nums[i]); // Get absolute value to find the index
            if (index == n)
                nums[0] = -Math.abs(nums[0]); // Mark presence of n using nums[0]
            else
                nums[index] = -Math.abs(nums[index]); // Mark presence of index
        }
        
        // Step 3: Identify the missing number
        for (int i = 1; i < n; i++) {
            if (nums[i] > 0) return i; // First missing index
        }
        
        // Special case: If nums[0] is positive, then n is missing
        if (nums[0] > 0) return n; 
        
        return n + 1; // If all 1 to n exist, return n+1
    }
    /* Approach 3 : Cycle Sort 
    */
    public int firstMissingPositive(int[] A) {
        int n = A.length;
        int i = 0;
        while(i < n ){
            int correctIndex = A[i]-1;
            if( A[i]>0 && A[i]<=n && A[i] != A[correctIndex] ){
                swap(A, i, correctIndex);
            }else{
                i++;
            }
        }
        for(int j=0; j<n; j++){
            if(j+1 != A[j]) return j+1;
        }
        return n+1;
    }
    void swap(int[] A, int p, int q){
        int temp = A[p];
        A[p] = A[q];
        A[q] = temp;
    }
}