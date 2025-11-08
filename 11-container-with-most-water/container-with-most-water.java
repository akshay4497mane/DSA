class Solution {
    public int maxArea(int[] height) {
        int N = height.length, L = 0, R = N-1, ans =0;
        while(L<R){ 
            int currWaterLR = Math.min(height[L], height[R]) * (R-L);
            ans = Math.max(ans, currWaterLR );
            System.out.println("L :" + L + " R :" + R + "currWater :" + currWaterLR);
            if(height[L] < height[R]){
                L++;
            }else{
                R--;
            }
        }
        return ans;
    }
}