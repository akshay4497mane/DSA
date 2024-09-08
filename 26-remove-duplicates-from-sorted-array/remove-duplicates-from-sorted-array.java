class Solution {
    public int removeDuplicates(int[] nums) {
        int i = 0, ansi =0, n = nums.length;
        while( i < n ){
            int curr = nums[i];
            nums[ansi++] = nums[i++];
            while( i<n && nums[i] == curr ){
                i++;
            }
        }
        return ansi;
    }
}