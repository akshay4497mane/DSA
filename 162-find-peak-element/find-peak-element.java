/*Revision Notes (read this first)
• Peak ≠ maximum
A peak is a local maximum: greater than immediate neighbors.
Global peak (maximum element) is one specific peak, but the problem allows any peak.

• Why binary search works on unsorted array
Decision is based on local slope (nums[mid] vs nums[mid+1]), not ordering.
If slope rises → a peak must exist on the right. If slope falls → a peak must exist on the left.

• Key invariant
At every step, we discard half the array that is guaranteed to have no peak.

• Goal
Return any peak index, not necessarily the global maximum.

---

Global Peak vs Local Peak (Very Important)

Global Peak
• Maximum value in the entire array
• Unique if all elements are distinct
• Example: [0,2,1,3,5,6,4,5,3] → global peak = 6 (index 5)

Local Peak
• Element greater than immediate neighbors
• Can be multiple
• Example above → local peaks at index 1 (2), 5 (6), 7 (5)

Problem asks for any local peak, not global.

Approach 1: Brute Force Linear Scan
Summary / Intuition
Check every element and verify peak condition using neighbors.
Guaranteed to find at least one peak because boundaries are −∞.

Time Complexity: O(n)
Space Complexity: O(1)
*/

class Solution1 {
    public int findPeakElement(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            boolean left = (i == 0) || nums[i] > nums[i - 1];
            boolean right = (i == n - 1) || nums[i] > nums[i + 1];
            if (left && right) return i;
        }
        return -1;
    }
}
/*
Approach 2: Recursive Binary Search
Summary / Intuition
Compare mid with mid+1 to decide direction.
Peak must exist in the direction of the slope.

Time Complexity: O(log n)
Space Complexity: O(log n) (recursion stack)

*/
class Solution {
    public int findPeakElement(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }

    private int search(int[] a, int l, int r) {
        if (l == r) return l;

        int mid = l + (r - l) / 2;

        if (a[mid] < a[mid + 1])
            return search(a, mid + 1, r); // rising
        return search(a, l, mid);         // falling
    }
}

/*
Approach 3: Iterative Binary Search (Best / Interview Preferred)
Summary / Intuition
Use local slope to eliminate half of the array.
Stop when search space reduces to one element — that index is a peak.
Time Complexity: O(log n)
Space Complexity: O(1)
*/
class Solution_3 {
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;

            if (nums[mid] < nums[mid + 1])
                l = mid + 1; // peak on right
            else
                r = mid;     // peak on left or mid
        }
        return l;
    }
}
/*
Why Binary Search Never Loses a Peak
• nums[mid] < nums[mid+1]
→ you are going uphill → peak exists ahead
• nums[mid] > nums[mid+1]
→ you are going downhill → peak exists behind

This holds even in unsorted arrays.

One-liner to say in interview

“We don’t search for the global maximum; we follow the slope direction because a local peak is guaranteed to exist on that side.”
*/