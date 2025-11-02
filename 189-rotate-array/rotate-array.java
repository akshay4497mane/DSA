class Solution {
/*
Approach 1 : Brute Force | Rotate 1 step at a time | repeat k times | Time : O(N x K)
    public void rotate(int[] nums, int k) {
        // speed up the rotation
        k %= nums.length;
        int temp, previous;
        for (int i = 0; i < k; i++) {
            previous = nums[nums.length - 1];
            for (int j = 0; j < nums.length; j++) {
                temp = nums[j];
                nums[j] = previous;
                previous = temp;
            }
        }
    }
Approach 2 : Take new array, copy elements at i+k %n index | Time: O(N) | Space : O(N)
*/
    
/* Approach 3 | Use Reverse() | 3 pass | Time : O(N), Space O(1) 
1 2 3 4 5 6 7
Reverse(0, n-1)
7 6 5 4 3 2 1
Reverse(0, k-1)
5 6 7 | 4 3 2 1
Reverse(k, n-1) 
5 6 7 | 1 2 3 4
*/
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k%n;
        reverse(nums, 0,  n-1);
        Arrays.stream(nums).forEach(x -> System.out.print(x + " "));
        reverse(nums, 0,  k-1);
        reverse(nums, k,  n-1);
        Arrays.stream(nums).forEach(x -> System.out.print(x + " "));
    }
    void reverse(int[] nums, int start, int end){
        int n = nums.length;
        if(start <0 || start>=n || end<0 || end>=n || start>=end) return;
        while(start<end){
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }        
    }
}
