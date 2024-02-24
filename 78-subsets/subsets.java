import java.math.BigInteger;
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        //backtrack(0, new ArrayList<Integer>(), nums, ans);
        //backtrack2(0, new ArrayList<Integer>(), ans, nums);    
        printAllSubSet(nums, ans);    
        return ans;
    }
//Approach 3 : Using Bitwise Maths
void printAllSubSet(int[] nums, List<List<Integer>> ans ){
    int N = nums.length;
    int totalSets = (int)Math.pow(2, N) - 1;
    for( int i=0; i <= totalSets; i++){//001, 010.....110, 111
        List<Integer> currAns = new ArrayList<Integer>();
        for(int j=0; j<N; j++){ //there are N bits in each i
                //if ((i & (1 << j)) != 0) {//is jth bit set for the current "i"?
                if(BigInteger.valueOf(i).testBit(j)){
                    currAns.add(nums[j]);// add j'th element to the ans
                }
        }
        ans.add(currAns);
    }
}
//Approach 2 : 0/1 knapsack, For every element either pick or Dont pick, 
//Time Complexity: O (N(2^N)) | For every element there will be two cases, so O (2^N) and time taken to copy N elements.
// https://www.topcoder.com/thrive/articles/print-all-subset-for-set-backtracking-and-bitmasking-approach
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
