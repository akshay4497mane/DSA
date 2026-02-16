class Solution {
    //min(Lmax, Rmax) - currHeight
//Revision 8 Nov 2025 //Coding 12 Nov
//Revision 16 feb 2026

/* Approach 4 : Two Pointer Approach | Time : O(N) | Space: O(1)   */
    public int trap_approach3(int[] height) {
        int N = height.length, ans = 0, left=0, right=N-1, leftMax = 0, rightMax = 0;
        while( left < right ){
            if( height[left] < height[right] ){
                if( height[left] >= leftMax ){
                    leftMax = height[left];
                }else{
                    ans += leftMax - height[left];
                }
                ++left;
            }else{
                if( height[right] >= rightMax ){
                    rightMax = height[right];
                }else{
                    ans += rightMax - height[right];
                }
                --right;
            }
        }
        return ans;
    }

    //Approach 3 : Dont use LMAX, use only RMAX => calculate on the fly
    public int trap(int[] height) {
        int n = height.length;
        if (n == 0) return 0;
        int ans = 0;
        // Step 1: Precompute RMAX
        int[] rMax = new int[n];
        int maxTillNow = 0;
        for (int i = n - 1; i >= 0; i--) {
            maxTillNow = Math.max(maxTillNow, height[i]);
            rMax[i] = maxTillNow;
        }
        // Step 2: Traverse from left
        int leftMax = 0;
        for (int i = 0; i < n; i++) {
            leftMax = Math.max(leftMax, height[i]); // Update leftMax dynamically
            ans += Math.min(leftMax, rMax[i]) - height[i];         // Water trapped at index i
        }
        return ans;
    }
    
    /*Approach 2 : DP approach | Precalculate & Store LMAX, RMAX 
    for every i(1 to N-2), find LMAX, RMAX,  
    find sum of all answers ( min(lmax, rmax) - h[i] )
    Time: O(N), Space: O(N)
    */
    public int trap_DP(int[] height) {
        int N = height.length, ans = 0;
        int[] LMAX = new int[N];
        for(int i=0, maxTillNow=0; i<N; i++){
            maxTillNow = Math.max(maxTillNow, height[i]);
            LMAX[i] = maxTillNow;
        }
        int[] RMAX = new int[N];
        for(int i=N-1, maxTillNow=0 ; i>=0; i--){
            maxTillNow = Math.max(maxTillNow, height[i]);
            RMAX[i] = maxTillNow;
        }
        // Arrays.stream(LMAX).forEach(System.out::print);
        // System.out.println(""); 
        // Arrays.stream(RMAX).forEach(System.out::print);
        for(int i=1; i<N-1; i++){ //i=0,i=N-1 is skipped as it will have 0 water
            ans += Math.min(LMAX[i], RMAX[i]) - height[i];
        }
        return ans;
    }    
    //Approach 1 : Brute Force, for every i(1 to N-2), find LMAX, RMAX,  
    //find sum of all answers ( min(lmax, rmax) - h[i] )
    //Time : O(N^2)
    public int trap_BruteForce(int[] height) {
        int N = height.length;
        int ans = 0;
        for(int i=1; i<N-1; i++){
            int lmax = 0;
            for(int j=0; j<=i; j++) lmax = Math.max(lmax, height[j]);
            int rmax = 0;
            for(int j=i; j<N; j++) rmax = Math.max(rmax, height[j]);
            ans += Math.min(lmax, rmax) - height[i];
        }
        return ans;
    }
}
