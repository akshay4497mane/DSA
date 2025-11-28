class Solution {
/*
Approach: Binary Search
Ans will lie between (positive)1...9(max of array)
Video Aryan : https://www.youtube.com/watch?v=wIFX0quUQBw
Time complexity: O(nlogk) : O(N) for isPossible, O(log K) for Binary search 1..K
Space complexity: O(1)
*/
    public int minimumSize(int[] nums, int maxOperations) {
        int left = 1, right = Arrays.stream(nums).max().getAsInt(), mid, ans=right;
        //Integer[] arr = {5, 2, 9, 1, 7}; int max = Collections.max(Arrays.asList(arr));
        //1...4...9
        while( left<=right ){
            mid = left + (right-left)/2;
            if( isPossible(maxOperations, mid, nums) ){
                ans=mid;
                right = mid-1;
            }else{
                left=mid+1;
            }
        }
        return ans;
    }
    Boolean isPossible(int maxOperations, int maxBallsInBag, int[] nums){
        int operations = 0;
        for(int num : nums){
            operations += (int)Math.ceil( (double)num/maxBallsInBag ) -1;
            if(operations> maxOperations)
                return false;
        }
        return true;
    }
}