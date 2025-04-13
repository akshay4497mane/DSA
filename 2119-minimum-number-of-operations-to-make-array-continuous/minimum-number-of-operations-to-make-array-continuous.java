class Solution {
    public int minOperations(int[] nums) {
        int N = nums.length;
        //sort array
        Arrays.sort(nums); //nlogn

        //remove duplicates
        List<Integer> newNums = new ArrayList<>();
        newNums.add(nums[0]);
        for (int i = 1; i < N; i++) { //O(N)
            if (nums[i] != nums[i - 1]) {
                newNums.add(nums[i]);
            }
        }
        newNums.stream().forEach(x -> System.out.print(x + " "));
        //sliding window
        int right=0;
        int ans=N; //worst case : we need N operations
        for(int left=0;left<newNums.size(); left++){
            while(right<newNums.size() && newNums.get(right)<= (newNums.get(left)+N-1)){
                right++;
            }
            int count = right-left;
            ans = Math.min(ans, N-count);
        }
        return ans;
    }
}