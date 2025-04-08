class Solution {
    public int lengthOfLIS(int[] nums) {
        int n=nums.length;
        int[] LIS = new int[n];

// [0,1,0,3,2,3] | 
//              i
//LIS[1,2,1  3,3,4]
                 //4
        Arrays.fill(LIS, 1);
        for(int i=0; i<n; i++){
            for(int j=0; j<i ; j++){
                if(nums[j] < nums[i]){
                    LIS[i] = Math.max(LIS[i], LIS[j] + 1); //Forgot MAX => failed [0,1,0,3,2,3]
                }
            }
        }
        int ans = LIS[0];
        for(int i=0;i<n;i++){
            ans = Math.max(ans, LIS[i]);
        }
        return ans;
    }
}