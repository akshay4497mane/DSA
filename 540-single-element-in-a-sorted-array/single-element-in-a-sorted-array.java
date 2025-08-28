class Solution {
    public int singleNonDuplicate(int[] nums) {
        int low = 0, high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            // Case 1: pair is (mid, mid+1)
            if (mid + 1 <= high && nums[mid] == nums[mid + 1]) {
                int leftCount = mid - low;           // elements before the pair
                int rightCount = high - (mid + 1);   // elements after the pair

                if (leftCount % 2 == 1) {
                    high = mid - 1;  // single lies on the left
                } else {
                    low = mid + 2;   // single lies on the right
                }
            }
            // Case 2: pair is (mid-1, mid)
            else if (mid - 1 >= low && nums[mid] == nums[mid - 1]) {
                int leftCount = (mid - 1) - low;   // elements before the pair
                int rightCount = high - mid;      // elements after the pair

                if (leftCount % 2 == 1) {
                    high = mid - 2;  // single lies on the left
                } else {
                    low = mid + 1;   // single lies on the right
                }
            }
            // Case 3: mid is the single
            else {
                return nums[mid];
            }
        }

        return nums[low];
    }
}
