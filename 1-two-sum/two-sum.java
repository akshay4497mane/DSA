/*
Problem: Find two indices i, j in an array such that A[i] + A[j] = target sum.
Sample Input: A = [2, 7, 11, 15], sumNeeded = 9
Sample Output: [0, 1] (Since 2 + 7 = 9)

Alternative Approaches:
1. Brute Force: Use two loops to check all pairs. O(N^2) time, O(1) space.
2. Sorting + Binary Search: Sort array, use binary search for complement. O(N log N) time.
3. HashMap: Store complements for O(1) lookup. O(N) time, O(N) space.
*/

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] A, int sumNeeded) {
        int n = A.length;
        Map<Integer, Integer> map = new HashMap<>(); // Stores value -> index
        
        for (int i = 0; i < n; i++) {
            int target = sumNeeded - A[i]; // Compute required complement
            
            if (map.containsKey(target)) { // If complement exists, return indices
                return new int[]{map.get(target), i};
            }
            
            map.put(A[i], i); // Store current number and its index for future lookups
        }
        
        return new int[]{-1, -1}; // Return invalid indices if no pair found
    }
}
