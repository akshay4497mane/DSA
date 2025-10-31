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
/* Approach 2 : Optimize search using HashSet | Dont try for num if num-1 is already part of longest sequence
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
        for(int num : numSet){//if we iterate in Array(nums) i gets TLE
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
/*
Approach 3 :
Sort elements and then count sequences where nums[i] == nums[i-1]+1
*/
    public int longestConsecutive_3(int[] nums) {
        if(nums.length == 0) return 0;
        Arrays.sort(nums);
        int longestStreak = 1;
        int currStreak = 1;
        for(int i=1; i<nums.length; i++){
            if( nums[i] != nums[i-1] ){
                if( nums[i] == nums[i-1]+1 ){
                    currStreak += 1;
                }else{
                    longestStreak = Math.max(longestStreak, currStreak);
                    currStreak = 1;
                }
            }
        }
        return longestStreak;
    }
}