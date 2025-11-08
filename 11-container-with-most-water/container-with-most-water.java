class Solution {
/*
Approach : 2 Pointer approach
L, R
Water = height * length = Math.min(height[L], height[R]) * (R-L);
Time : O(N)
*/
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
    public static int maxArea_Optimized(int[] height) {
        int maxArea = 0;
        int len = height.length;
        int i = 0, j = len - 1;
        while(i < j){
            int lowest = height[i] < height[j] ? height[i] : height[j];
            int area = lowest * (j - i);
            maxArea = area > maxArea ? area : maxArea;

            while(i < len && height[i] <= lowest) i++;
            while(j > i && height[j] <= lowest) j--;
        }

        return maxArea;
    }    
}