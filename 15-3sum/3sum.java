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