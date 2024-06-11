class Solution {
//Approach 2 :
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(0, new ArrayList<Integer>(), ans, nums );
        return ans;
    }
    void backtrack(int start, List<Integer> currAns, List<List<Integer>> ans, int[] nums ){
        ans.add(new ArrayList<Integer>(currAns));
        for(int i = start; i < nums.length ; i++){
            if( i== start || nums[i] != nums[i-1] ){
                currAns.add(nums[i]);
                backtrack( i+1, currAns, ans, nums);
                currAns.remove(currAns.size()-1);
            }
        }
    }

//Approach 1 : Using Bitwise operations + HashSet 
    public List<List<Integer>> subsetsWithDup_Bitwise(int[] nums) {
        int N = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        int totalSets = (int)Math.pow(2, N);
        for(int i=0; i<totalSets; i++){
            List<Integer> currAns = new ArrayList<>();
            StringBuilder currS = new StringBuilder();
            for(int j=0;j<N;j++){
                if( ( i & (1<<j)) != 0){
                    currAns.add( nums[j] );
                    currS.append(nums[j]).append(",");
                }
            }
            if( !seen.contains(currS.toString()) ){
                seen.add(currS.toString());
                ans.add(currAns);
            }
        }
        return ans;
    }
}