class Solution {
    /*
    Approach : Try all possible subarrays, count longest length such that numZeros <=1 
    Time : O(N^2) | Space : O(N) 
    */
    public int findMaxConsecutiveOnes_1(int[] nums) {
        int longestSequence = 0;
        for(int left=0; left<nums.length; left++){
            int numZeroes=0;
            for(int right=left; right<nums.length; right++){
                if(nums[right]==0)
                    numZeroes += 1;
                if(numZeroes<=1)
                    longestSequence = Math.max(longestSequence, right-left+1);
            }
        }
        return longestSequence;
    }
    /*
    Approach2 : sliding window(left-right)
    expand toward right,
    check if valid sequence, if yes update ans and continue right
    if no valid, increment left
    valid Sequence : numZeroes<=1
    Time : O(N) | Space : O(1)
    */
    public int findMaxConsecutiveOnes(int[] nums) {
        int longestSequence =0;
        int left=0, right=0, numZeroes = 0;
        while(right<nums.length){
            if(nums[right]==0) numZeroes++;
            //if window is invalid, increment from left untill window becomes valid
            while( numZeroes==2 ){
                if( nums[left]==0 )
                    numZeroes--;
                left++;
            }
            longestSequence = Math.max(longestSequence, right-left+1);
            right++;
        }
        return longestSequence;
    }
/*
    public int findMaxConsecutiveOnes(int[] nums) {
        in ans = Integer.MIN_VALUE;
        int i=0;
        int before0Count = 0, after0Count=0;
        bool found0 = true;
        while(i<nums.length){
            while(nums[i]==1)}{
                before0Count++;
                i++;
            } 
            //found0 = true
            i++; //skip one 0
            while(nums[i]==1)}{
                after0Count++;
                i++;
            }
            int currAns = before0Count + 1 + after0Count;
            ans = Math.max(ans, currAns);

        }
    }
*/
}