class Solution {
    public void sortColors(int[] nums) {
        int p0 =0, curr =0;
        int p2=nums.length-1;
        int tmp;
        while(curr <= p2){
            if(nums[curr]==0){
                //swap p0, curr
                tmp = nums[p0];
                nums[p0++] = nums[curr];
                nums[curr++] = tmp;
            }else if( nums[curr] == 2){
                tmp = nums[curr];
                nums[curr] = nums[p2];
                nums[p2--] = tmp;
            }else{
                curr++;
            }
        }
    }

    /* The problem is known as Dutch National Flag Problem
        Time : O(N) two passes
        Space : O(1) | 3 int    
    */
    public void sortColors_2(int[] nums) {
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