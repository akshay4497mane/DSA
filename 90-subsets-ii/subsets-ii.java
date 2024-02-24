class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
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