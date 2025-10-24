class Solution {
    public void moveZeroes(int[] nums) {
        int i = 0, j = 0, N = nums.length;

        while (j < N) {
            if (nums[j] != 0) {
                // swap only if i and j are different
                if (i != j) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
                i++;
            }
            j++;
        }
    }
}
