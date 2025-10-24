class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
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