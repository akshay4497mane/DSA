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
    public int findMaxConsecutiveOnes_2(int[] nums) {
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
    Approach 3:
    For each zero, count consecutive 1s before it and consecutive 1s after it.
    Then, max length = leftOnes + 1 (flip this zero) + rightOnes.
    Keep track of maximum length across all zeros.
    
    Time: O(N)
    Space: O(1)
    */
    public int findMaxConsecutiveOnes_3(int[] nums) {
        int n = nums.length;
        int maxLen = 0;
        
        for (int i = 0; i < n; i++) {
            // only check when nums[i] == 0
            if (nums[i] == 0) {
                int left = 0, right = 0;
                
                // count 1s on the left
                int l = i - 1;
                while (l >= 0 && nums[l] == 1) {
                    left++;
                    l--;
                }
                
                // count 1s on the right
                int r = i + 1;
                while (r < n && nums[r] == 1) {
                    right++;
                    r++;
                }
                
                // flip this 0 → include left + right + 1
                maxLen = Math.max(maxLen, left + 1 + right);
            }
        }

        // special case: all 1s, no zero to flip
        if (maxLen == 0) {
            return n;
        }
        
        return maxLen;
    }

  /*
    Approach 3.2 (Optimized O(N)):
    Precompute consecutive 1s to the left and right of each index.
    For each 0, maxLen = left[i-1] + 1 + right[i+1].
    */
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        
        // count consecutive 1s to the left of each index
        for (int i = 1; i < n; i++) {
            if (nums[i-1] == 1)
                left[i] = left[i-1] + 1;
        }

        // count consecutive 1s to the right of each index
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i+1] == 1)
                right[i] = right[i+1] + 1;
        }

        int maxLen = 0;
        boolean hasZero = false;
        
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                hasZero = true;
                int total = left[i] + 1 + right[i];
                maxLen = Math.max(maxLen, total);
            }
        }

        // if array has no zero, entire array is max sequence
        return hasZero ? maxLen : n;
    }
/*
Approach 3:
--> For each 0, coun 1's before and 1s after- > calculate ans, Keep track of max
-->

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