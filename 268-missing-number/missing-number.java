class Solution {
    //Approach 1 : Sort Nums array, ensure 0, last element, for i : ensure nums[i] == nums[i-1]
        //Time : O( N logN ) | Space : O(1)
    
/* Approach 2 : use HashSet | Time O(N) | Space : O(N)
public int missingNumber(int[] nums) {
    Set<Integer> numSet = new HashSet<>();
    for(int num : nums) numSet.add(num);
    int expectedCount = nums.length + 1;
    for(int num = 0 ; num < expectedCount ; num++){
        if(!numSet.contains(number))
            return num;
    }
    return -1;
}
*/
/*
    //Approach 3 : using Boolean Array | Time:O(N), Space O(N)
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
*/
    //Approach 4 : Using XOR
    public int missingNumber(int[] nums) {
        int expectedXor = 0;
        for(int i=0; i <= nums.length; i++)
            expectedXor ^=i;
        int actualXor = 0;
        for(int num : nums)
            actualXor ^= num;
        return expectedXor ^ actualXor;    
    }
/*
    //Approach 5 : Using gauss formula(sum of elements)
    public int missingNumber(int[] nums) {
        int N = nums.length;
        int expectedSum = ( N * (N+1)) / 2;
        int actualSum = 0;
        for(int num : nums) actualSum += num;
        return expectedSum - actualSum;
    }
*/
}