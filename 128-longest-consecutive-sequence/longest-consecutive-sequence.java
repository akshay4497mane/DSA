class Solution {
/* Approach 1 : Brute force | O(N^3) time | O(1) space
consider each num, 
Try to start sequence from there,
search if num+1 is part of array
Keep track of longest sequence
*/
    public int longestConsecutive_1(int[] nums) {
        int longestStreak = 0;
        for(int num : nums){
            int currNum = num;
            int currStreak = 1;
            while(arrayContains(nums, currNum+1)){
                currNum += 1;
                currStreak += 1;
            }
            longestStreak = Math.max(longestStreak, currStreak);
        }
        return longestStreak;
    }
    private boolean arrayContains(int[] nums, int target){
        for(int num : nums){
            if(num == target) 
                return true;
        }
        return false;
    }
/* Approach 2 : Optimize arrayContains using HashSet | Dont try for num if num-1 is already part of longest sequence
Time( O(N) ) | O(N) space
consider each num, 
Try to start sequence from there,
search if num+1 is part of array
Keep track of longest sequence
*/
    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for(int num : nums){
            numSet.add(num);
        }

        int longestStreak = 0;
        for(int num : numSet){
            int currNum = num;
            int currStreak = 1;
            if(!numSet.contains(currNum-1)){
                while(numSet.contains(currNum+1)){
                    currNum += 1;
                    currStreak += 1;
                }
                longestStreak = Math.max(longestStreak, currStreak);
            }
        }
        return longestStreak;
    }
}