class Solution {
    public void moveZeroes(int[] nums) {
        int nextNonZeroPos = 0;
        for (int curr = 0; curr < nums.length; curr++) {
            if (nums[curr] == 0) continue;
            if (curr != nextNonZeroPos) {
                int temp = nums[nextNonZeroPos];
                nums[nextNonZeroPos] = nums[curr];
                nums[curr] = temp;
            }
            nextNonZeroPos++;
        }
    }
/*
    public void moveZeroes(int[] nums) {
        int pos = 0; // next place to put non-zero
        for (int num : nums) {
            if (num != 0) nums[pos++] = num;
        }
        while (pos < nums.length) nums[pos++] = 0;
    }
*/
}
