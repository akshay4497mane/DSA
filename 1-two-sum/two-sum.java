class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] ans = new int[2];
        /*
        Approach 1: two loops
        Time O(N^2) , Space: O(1)
        
        for(int i = 0; i < nums.length ; i++){
            for(int j = i+1 ; j < nums.length ; j++ ){
                if(target == nums[i] + nums[j] ){
                    ans[0] = i;
                    ans[1] = j;
                }
            }
        }
        */
        /*
        Approach2:
        For each element curr, search Target - curr in rest of array, Search using Binary search(log N), using HashMap( O(1) )
        Time: O(N), Space: O(N)
        */
        Map<Integer, Integer> m = new HashMap<>(); //Value, index
        for(int i =0; i < nums.length ; i++){
            int newTarget = target - nums[i];
            if ( m.containsKey(newTarget) ){
                int newIndex = m.get(newTarget);
                ans[0] = i;
                ans[1] = newIndex;
                return ans;
            }else{
                m.put(nums[i] , i);
            }
        }
        return ans;
    }
}