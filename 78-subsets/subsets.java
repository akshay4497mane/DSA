class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        //backtrack(0, new ArrayList<Integer>(), nums, ans);
        backtrack2(0, new ArrayList<Integer>(), ans, nums);        
        return ans;
    }
//Approach 2 : 0/1 knapsack, For every element either pick or Dont pick
    void backtrack2(int i, List<Integer> currAns, List<List<Integer>> ans, int[] nums){
        if(i == nums.length){
            ans.add(new ArrayList<Integer>(currAns));
            return;
        }
        backtrack2(i+1, currAns, ans, nums);
            currAns.add(nums[i]);
        backtrack2(i+1, currAns, ans, nums);
            currAns.remove(currAns.size()-1);
    }

//Approach 1 : Using FOR loop, Time : O( 2^N )
    void backtrack(int start,List<Integer> currAns, int[] nums, List<List<Integer>> ans){
        System.out.println(currAns);
        ans.add( new ArrayList<Integer>(currAns) ); //Warn: use NEW keyword 
        for(int i=start; i<nums.length ; i++){
            currAns.add(nums[i]);
            backtrack(i+1, currAns, nums,ans); //Warn: dont pass START instead of i
            currAns.remove(currAns.size()-1);
        }
    }
}
