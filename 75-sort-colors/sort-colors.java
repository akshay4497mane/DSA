class Solution {
    public void sortColors(int[] nums) {
        int [] index = new int[3];
        for( int i=0; i<nums.length; i++ ){
            index[nums[i]]++;
        }
        int i = 0;
        for(int j=0; j<index[0]; j++){
            nums[i++] = 0;
        }

        for(int j=0; j<index[1]; j++){
            nums[i++] = 1;
        }

        for(int j=0; j<index[2]; j++){
            nums[i++] = 2;
        }
    }
}