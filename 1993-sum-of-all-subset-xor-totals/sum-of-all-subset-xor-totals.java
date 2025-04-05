class Solution {
    /*Approach 1 :
        Backtracking, Pick/dont pick each element | keep calculating sum 
    */
    int ans = 0;
    public int subsetXORSum(int[] nums) {
        helper(0, 0, nums);
        return ans;
    }
    void helper(int ind, int currXorSum, int[] nums ){
        if( ind==nums.length ){
            ans += currXorSum;
            return;
        }
        //not pic
        helper(ind+1, currXorSum, nums);
        //pick
        helper(ind+1, currXorSum^nums[ind], nums);
    }

    /*Print all subsets using pick/non pick recursion 
    */
    List<List<Integer>> allSubsets = new ArrayList<>();
    public int subsetXORSum_PrintALLSubsets(int[] nums) {
        subsets(nums, 0, new ArrayList<Integer>());
        allSubsets.stream().forEach( list -> {
            list.stream().forEach( num -> System.out.print(num + " "));
            System.out.println();
        });
        return -1;
    }
    //print all subsets
    void subsets(int[] nums, int ind, List<Integer> currList){
        if( ind == nums.length){
            allSubsets.add(new ArrayList<>(currList)); // Add a deep copy of current subset
            return;
        }

        //dont pick index
        subsets(nums, ind+1, currList);

        //pick index
            currList.add(nums[ind]);
        subsets(nums, ind+1, currList);
            currList.remove(currList.size()-1);
    }
}