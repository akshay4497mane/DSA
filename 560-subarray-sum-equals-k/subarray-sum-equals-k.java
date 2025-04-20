class Solution {
    public int subarraySum(int[] nums, int k) {
        int ans = 0, currSum = 0;
        Map<Integer, Integer> prefixSumMap = new HashMap<>(); //prefix, count
        prefixSumMap.put(0, 1);
        for( int n : nums ){
            currSum += n;
            int toFindPrefix = currSum - k;
            if( prefixSumMap.containsKey(toFindPrefix) ){
                ans += prefixSumMap.get(toFindPrefix);
            }
            prefixSumMap.put(currSum, 1+prefixSumMap.getOrDefault(currSum, 0));
        }
        return ans;
    }
}