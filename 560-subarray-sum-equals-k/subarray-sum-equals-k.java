class Solution {
    // LeetCode 560: Subarray Sum Equals K
    // Returns the number of contiguous subarrays whose sum equals k
    public int subarraySum(int[] nums, int k) {
        // HashMap to store:
        // key   -> prefix sum value
        // value -> number of times this prefix sum has appeared
        HashMap<Integer, Integer> prefixFreq = new HashMap<>();

        // BASE CASE:
        // Prefix sum = 0 has occurred once before processing any elements
        // This allows subarrays starting from index 0 to be counted
        prefixFreq.put(0, 1);

        // runningSum stores sum of elements from index 0 to current index
        int runningSum = 0;

        // count stores total number of valid subarrays
        int count = 0;

        // Traverse the array exactly once
        for (int i = 0; i < nums.length; i++) {

            // Add current element to running prefix sum
            runningSum += nums[i];

            // We want:
            // prefixSum[j] - prefixSum[i] = k
            // => prefixSum[i] = prefixSum[j] - k
            int neededPrefixSum = runningSum - k;

            // If this prefix sum was seen before,
            // each occurrence forms a valid subarray ending at index i
            if (prefixFreq.containsKey(neededPrefixSum)) {
                count += prefixFreq.get(neededPrefixSum);
            }

            // Store/update frequency of current runningSum
            // If already present, increment count
            // Otherwise, initialize with 1
            prefixFreq.put(
                runningSum,
                prefixFreq.getOrDefault(runningSum, 0) + 1
            );
        }

        // Return total count of subarrays whose sum equals k
        return count;
    }
}

/*
Asked in Project44 ( 05/02/2026) Aditya
Fulfilled Shipments

Description

We are tracking daily shipments for an e-commerce company.
Each day we record a pair (dispatched, returned),
i.e. the number of orders shipped and the number of orders returned respectively.

We want to find the number of continuous periods of days where the total number of successfully fulfilled orders is exactly K.

Return the total number of such continuous periods.

import java.util.HashMap;
import java.util.List;
class Solution {
    // orders: List of days
    // Each day = [dispatched, returned]
    // K: target fulfilled sum
    public static int countFulfilledPeriods(List<List<Integer>> orders, int K) {
        // Total number of days
        int n = orders.size();
        // HashMap to store prefix sum frequencies
        // key   -> prefix sum value
        // value -> number of times this prefix sum has appeared
        HashMap<Integer, Integer> prefixFreq = new HashMap<>();
        // BASE CASE:
        // Prefix sum = 0 is considered seen once
        // This allows subarrays starting from index 0 to be counted
        prefixFreq.put(0, 1);
        // runningSum stores sum of fulfilled orders from day 0 to current day
        int runningSum = 0;
        // count stores number of valid continuous periods
        int count = 0;
        // Traverse each day exactly once
        for (int i = 0; i < n; i++) {
            // Extract dispatched and returned for the day
            int dispatched = orders.get(i).get(0);
            int returned   = orders.get(i).get(1);
            // Calculate fulfilled orders for this day
            // fulfilled can be positive, zero, or negative
            int fulfilled = dispatched - returned;
            // Update prefix sum
            runningSum += fulfilled;
            // We want:
            // prefixSum[j] - prefixSum[i] = K
            // => prefixSum[i] = prefixSum[j] - K
            int neededPrefixSum = runningSum - K;
            // If this prefix sum was seen before,
            // each occurrence gives one valid subarray ending here
            if (prefixFreq.containsKey(neededPrefixSum)) {
                count += prefixFreq.get(neededPrefixSum);
            }
            // Store/update current prefix sum frequency
            prefixFreq.put(
                runningSum,
                prefixFreq.getOrDefault(runningSum, 0) + 1
            );
        }
        // Final answer
        return count;
    }
    // Example usage
    public static void main(String[] args) {
        // orders = [[5,6], [5,3]]
        List<List<Integer>> orders = List.of(
            List.of(5, 6),
            List.of(5, 3)
        );

        int K = 3;

        int result = countFulfilledPeriods(orders, K);

        // fulfilled = [-1, 2]
        // No subarray sums to 3
        System.out.println(result); // Output: 0
    }
}
*/