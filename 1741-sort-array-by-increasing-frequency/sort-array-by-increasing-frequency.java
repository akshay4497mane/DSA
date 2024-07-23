class Solution {
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for(int num : nums){
            freqMap.put(num, freqMap.getOrDefault(num, 0)+1);
        }
        
        // Java's Arrays.sort method doesn't directly support
        // sorting primitive arrays (int[]) with a lambda comparator.
        // We can convert the primitive int into Integer objects
        Integer[] numsObj = new Integer[nums.length];
        for(int i=0 ; i<nums.length; i++){
            numsObj[i] = nums[i];
        }
        Arrays.sort(numsObj, (a,b) -> { //Ascending order 1,2,3...
            int freqCompare = Integer.compare(freqMap.get(a), freqMap.get(b));
            if( freqCompare == 0 ){
                return Integer.compare(b,a); //Descending order 
            }
            return freqCompare;
        });
        //convert Integer Array back to int array
        for(int i=0;i<nums.length;i++)
            nums[i] = numsObj[i];

        return nums;
    }
}