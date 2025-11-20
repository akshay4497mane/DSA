class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> q = new ArrayDeque<>(); // store indices
        int n = nums.length;
        int[] out = new int[n - k + 1];

        int l = 0, r = 0, idx = 0;

        while (r < n) {
            // pop smaller values from the back
            while (!q.isEmpty() && nums[q.peekLast()] < nums[r]) {
                q.pollLast();
            }
            q.offerLast(r);

            // remove left index from the front if it is out of window
            if (q.peekFirst() < l) {
                q.pollFirst();
            }

            // window reached size k → record max
            if (r + 1 >= k) {
                out[idx++] = nums[q.peekFirst()];
                l++; // move left pointer
            }

            r++;
        }

        return out;
    }
}


class Solution_2_leetcodeEditorial {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> dq = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            while (!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) {
                dq.pollLast();
            }
            dq.offerLast(i);
        }
        res.add(nums[dq.peekFirst()]);
        for (int i = k; i < nums.length; i++) {
            if (dq.peekFirst() == i - k) {
                dq.pollFirst();
            }
            while (!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) {
                dq.pollLast();
            }
            dq.offerLast(i);
            res.add(nums[dq.peekFirst()]);
        }
        // Return the result as an array.
        return res.stream().mapToInt(i->i).toArray();
    }
}