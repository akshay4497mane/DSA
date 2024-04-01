class Solution {
    public int missingNumber(int[] nums) {
        int N = nums.length;
        boolean[] flag = new boolean[N+1];
        for(int num : nums){
            flag[num] = true;
        }
        int ind = 0;
        for( ; ind < N ; ind++ ){
            if(flag[ind] == false)
                return ind;
        }
        return ind;
    }
}