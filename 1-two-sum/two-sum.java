class Solution {

    //Approach 1 : Take 2 for loops, whenever A[i] + A[j] = sum , return i,j | Time:O(N^2)
    //Approach 2 : Take 1 for loop, search for sum-A[i] in rest of array, sort array, O(N log N) 
    //Approach 3 : take 1 loop, use hashmap for O(1) search | Time : O(N)
    public int[] twoSum(int[] nums, int sumNeeded) {
        Map<Integer, Integer> hm = new HashMap<>(); // num, index
        for(int i=0 ; i<nums.length ; i++ ){
            int toFind = sumNeeded - nums[i];
            hm.get(toFind);
            if(hm.containsKey(toFind)){
                int toFindIndex = hm.get(toFind);
                return new int[]{i, toFindIndex};
            }
            hm.put(nums[i], i);            
        }
    return new int[2];
    }
}