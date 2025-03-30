class Solution {
    public long minimumReplacement(int[] nums) {
        int n = nums.length;
        long numOfOperations = 0;
        long currLargest = nums[n-1];
        for(int i=n-2; i>=0; i--){
            if(nums[i]<=currLargest){
                currLargest = nums[i];
                continue;
            }
            long numElements;
            if(nums[i]%currLargest==0){
                numElements = nums[i]/currLargest;
                //currLargest remains as it is
            }else{
                numElements = nums[i]/currLargest + 1;
                currLargest = (long)nums[i]/numElements;
            }
            numOfOperations +=  numElements-1;
        }
        return numOfOperations;
    }
}