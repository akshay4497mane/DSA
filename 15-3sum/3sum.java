class Solution {
    //Triplet sum to Zero / Time Complexity : O(N^2), Space: O(1)
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums); // O(N log N)

        int size = nums.length;
        for(int i = 0; i < size-2; i++){ //O(N)
            if( i > 0 && nums[i] == nums[i-1] )//remove duplicates
                continue;
            int target = -1 * nums[i];  //TARGET element 
            int left = i+1 , right = size -1;
            //System.out.println("Start: "+ target + " " + left + " " + right);

            while(left < right){ //Two Pointer Approach O(N)
                if( target == nums[left] + nums[right]){
                    ans.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while(left < right && nums[left] == nums[left+1]) //remove duplicates 
                        left++;
                    while(left < right && nums[right] == nums[right-1])//remove duplicates
                        right--;
                    left++;
                    right--;
                }else if( target > nums[left] + nums[right]){
                    left++;
                }else{
                    right--;
                }
            }
        }
        return ans;   
    }
}


/*
Brute force: 
    -Approach 1 : 3 loops, find all triplet
    -Approach 2  : For every i element, find 2sum in rest of array with targetSum as -nums[i], remove duplicate elements in three places(for i, for left, for right)
-Approach 3 :(leetcode editorial) : Use HashSet to store values,  Time,: O(N^2), SpaceO(N) space 
For every i(0 to last),
    Seen = New HashSet() 
    for every j(i+1 to last), 
        If hashSet.contains( - (A[i] + A[j] ) ) => 
 add triplet
        seen.add ( A[j] )
*/
class Solution2 {
    public List<List<Integer>> threeSum_Approach2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length && nums[i] <= 0; ++i)
            if (i == 0 || nums[i - 1] != nums[i]) {
                twoSum(nums, i, res);
            }
        return res;
    }
    void twoSum(int[] nums, int i, List<List<Integer>> res) {
        var seen = new HashSet<Integer>();
        for (int j = i + 1; j < nums.length; ++j) {
            int complement = -nums[i] - nums[j];
            if (seen.contains(complement)) {
                res.add(Arrays.asList(nums[i], nums[j], complement));
                while (j + 1 < nums.length && nums[j] == nums[j + 1])
                    ++j;
            }
            seen.add(nums[j]);
        }
    }
}