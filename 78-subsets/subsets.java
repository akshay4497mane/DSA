class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(0, new ArrayList<Integer>(), nums, ans);
        return ans;
    }
    void backtrack(int start,List<Integer> currAns, int[] nums, List<List<Integer>> ans){
        System.out.println(currAns);
        ans.add( new ArrayList<Integer>(currAns) );
        for(int i=start; i<nums.length ; i++){
            currAns.add(nums[i]);
            backtrack(i+1, currAns, nums,ans);
            currAns.remove(currAns.size()-1);
        }
    }
}
