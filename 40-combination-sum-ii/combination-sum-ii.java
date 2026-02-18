class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        backtrackAndSum(candidates, target, 0, new ArrayList<>(), ans);
        return new ArrayList<>(ans);
    }
    void backtrackAndSum(int[] arr, int sum, int index, ArrayList<Integer> list, List<List<Integer>> ans){
        if(sum == 0 ){
            ans.add(new ArrayList<>(list));
            return;
        }
        if( sum<0  || index>= arr.length ) return;
        list.add( arr[index] );
        backtrackAndSum(arr, sum-arr[index], index+1, list, ans);
        list.remove( list.size()-1 );
        // duplicate logic
        for(int j=index+1; j<arr.length; j++ ){
            if( arr[j] != arr[index] ){
                backtrackAndSum(arr, sum, j, list, ans);
                break;            }
        }
    }
}