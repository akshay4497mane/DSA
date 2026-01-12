/*
Problem: Minimum Moves to Equal Array Elements

Key intuition (memorize this)
Incrementing n−1 elements by 1 is equivalent to decrementing exactly 1 element by 1.
So instead of pushing all numbers up, we pull numbers down.
Best final value is the minimum element in the array.

Why minimum?
Any target higher than min causes unnecessary extra moves.
Minimum guarantees least total decrements.

---
Approach 1: Direct simulation (brute force)
Idea
Actually perform the operation: in each move, increment n−1 elements until all equal.

Why it fails
Values grow unbounded, too many states, impossible for n up to 10⁵.

Time  : Unbounded / exponential
Space : O(n)

Used only to explain intuition, never implement.

---

Approach 2: Greedy via decrement view
Idea
Treat each move as decrementing one element by 1.
To equalize, reduce every element to the minimum.

Moves needed
Sum of (nums[i] − min)

Time  O(n)
Space O(1)

---

Approach 3: Mathematical optimization
Idea
Rewrite the sum directly.
moves = (nums[0] + nums[1] + ... + nums[n−1]) − min * n

Same logic as Approach 2, just compressed.

Time O(n)
Space O(1)

Common mistakes
• Thinking target should be max element
• Missing overflow → not using long for sum
• Trying to simulate operations
*/
class Solution {
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int num : nums) min = Math.min(min, num);
        int moves = 0;
        for (int num : nums) 
            moves += num - min;
        return moves;
    }

    public int minMoves_2(int[] nums) {
        int min = Integer.MAX_VALUE;
        long sum = 0;
        for (int x : nums) {
            min = Math.min(min, x);
            sum += x;
        }
        return (int)(sum - (long)min * nums.length);
    }
}
