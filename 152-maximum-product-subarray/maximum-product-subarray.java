class Solution {
//O(N) DP solution, handles 0, -, + cases| currMin is needed for -ve values
public int maxProduct_Optimal(int[] nums) {
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
//Brute Force, O(N^2)
public int maxProduct(int[] nums) {
    if( nums.length == 0 ) return 0;
    int ans = nums[0];
    for(int i=0; i<nums.length;i++){
        int prodSum = 1;
        for(int j=i;j<nums.length;j++){
            prodSum *= nums[j];
            ans = Math.max(ans, prodSum);
        }
    }
    return ans;
}

}