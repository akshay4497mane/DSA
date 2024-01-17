class Solution {
    public void moveZeroes(int[] nums) {
        int count = 0; // count Non Zero Elements
        int N = nums.length;
        for(int i=0; i<N ; i++){ //First shift ALL Non Zero elements to start of array
            if( nums[i] != 0 ){
                nums[count++] = nums[i];
            }
        }
        for( ; count<N ; ) nums[count++] = 0; //Fill the remaining array with Zeroes
    }
}