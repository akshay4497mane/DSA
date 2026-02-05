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
class Solution {
    public static int countFulfilledPeriods(int[] dispatched, int[] returned, int K) {
        int n = dispatched.length;

        HashMap<Integer, Integer> prefixFreq = new HashMap<>();
        prefixFreq.put(0, 1); // base case: sum 0 seen once

        int runningSum = 0;
        int count = 0;

        for (int i = 0; i < n; i++) {
            int fulfilled = dispatched[i] - returned[i]; // compute on the fly
            runningSum += fulfilled;                      // update prefix sum

            int needed = runningSum - K;                  // target prefix to look for
            if (prefixFreq.containsKey(needed)) {
                count += prefixFreq.get(needed);          // add all valid starts
            }

            // store current prefix sum frequency
            prefixFreq.put(runningSum, prefixFreq.getOrDefault(runningSum, 0) + 1);
        }
        return count;
    }
}
*/