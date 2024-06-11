class Solution {
    public int[] twoSum(int[] nums, int target) {
        
        /*
        //APPROACH 1 : Brute Force O(N^2) | 88ms
        for(int i=0; i<nums.length ; i++){
            for(int j=i+1; j<nums.length; j++){
                if( i!=j && nums[i] == target-nums[j] )
                    return new int[]{i,j};
            }
        }
        */
        
        /*
        Approach2:
        For each element curr, search Target - curr in rest of array, Search using Binary search(log N), using HashMap( O(1) )
        Time: O(N), Space: O(N) | 2ms
        */        
        Map<Integer, Integer> m = new HashMap<>(); // Number, index
        for(int i =0; i<nums.length; i++ ){
            int newTarget = target-nums[i];
            if(m.containsKey(newTarget)){
                return new int[]{i, m.get(newTarget)};
            }else{
                m.put(nums[i], i);
            }            
        }
        return new int[2];
    }
}