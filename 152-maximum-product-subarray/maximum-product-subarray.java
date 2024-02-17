class Solution {
public int maxProduct(int[] nums) {
    if(nums.length == 0 ) return 0;
    int currMax = nums[0], currMin = nums[0], ans = currMax;
    for(int i=1; i<nums.length; i++){
        int curr = nums[i];
        int newMax = Math.max(curr, Math.max(currMax*curr, currMin * curr));
        currMin = Math.min(curr, Math.min(currMax*curr, currMin * curr));
        currMax = newMax;
        ans = Math.max(ans , currMax );
    }
    return ans;
}
}