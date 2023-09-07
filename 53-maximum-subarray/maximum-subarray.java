class Solution {
    public int maxSubArray(int[] nums) {
        int N = nums.length, currSum = 0, maxSum = Integer.MIN_VALUE;
        /*//Kadane's Algorithm Return Maximum possible Sum for a Continuous SubArray
        for(int i = 0; i<N ;i++){
            currSum = Math.max( currSum + nums[i] , nums[i] );
            maxSum = Math.max( currSum, maxSum);
        }
        */
        int maxStart =0, maxEnd = 0, currStart = 0, currEnd = 0;
        for(int i = 0; i < N ;i++){
            if( currSum + nums[i] > nums[i] ){//Add nums[i]
                currSum = currSum + nums[i]; currEnd = i;
                if(currSum > maxSum){
                    maxSum = currSum; maxStart = currStart; maxEnd = currEnd;
                }
            }else{//Dont add nums[i], start new subArray from nums[i]
                currSum = nums[i]; currStart = currEnd = i;
                if(currSum > maxSum){
                    maxSum = currSum; maxStart = currStart; maxEnd = currEnd;
                }
            }
        }
        System.out.print("Max Sum SubARRAY: ");
        for(int i= maxStart; i<= maxEnd; i++)
            System.out.print(nums[i]);
        return maxSum;
    }
}