class Solution {  // Kadane's Algorithm for finding the maximum subarray sum | O(N) Time complexity
    public int maxSubArray(int[] A) {
        int N = A.length; // Length of the input array
        int maxSum,currSum; // Variable to store the maximum sum, current sum
        // Base cases
        if (N <= 0) return 0; // If the array is empty, return 0
        if (N == 1) return A[0]; // If the array has only one element, return that element
        // Initialize current sum and maximum sum with the first element of the array
        currSum = maxSum = A[0];
        // Traverse the array starting from the second element
        for (int i = 1; i < N; i++) {
            // Check if adding the current element improves the current subarray
            if (A[i] > currSum + A[i]) { // Start a new subarray with the current element
                currSum = A[i];
            } else { // Include the current element in the existing subarray
                currSum = currSum + A[i];
            }
            maxSum = Math.max(maxSum, currSum);// Update the maximum sum
        }
        return maxSum;
    }
}