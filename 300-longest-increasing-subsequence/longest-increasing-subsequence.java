class Solution {
    //nums = [0,1,0,3,2,3]
    public int lengthOfLIS(int[] nums) {
        int N = nums.length, ans = 1;
        int[] DP = new int[N]; Arrays.fill(DP, 1);
        for(int i=1; i<N; i++){
            for(int j=0; j<i; j++){ // Can we increase any of previous LIS answers using nums[i]
                if(nums[i] > nums[j])
                    DP[i] = Math.max(DP[i], DP[j]+1);
            }
            ans = Math.max(ans, DP[i]);
        }
        return ans;
    }

    public int lengthOfLIS_2(int[] nums) {
        int n=nums.length;
        int[] LIS = new int[n];
        int[] prevIndex = new int[n];
        Arrays.fill(LIS, 1);
        Arrays.fill(prevIndex, -1);
        for(int i=0; i<n; i++){
            for(int j=0; j<i ; j++){
                if(nums[j] < nums[i]){
                    //LIS[i] = Math.max(LIS[i], LIS[j] + 1); //Forgot MAX => failed [0,1,0,3,2,3]
                    if(LIS[j] + 1 > LIS[i]){
                        prevIndex[i] = j;
                        LIS[i] = LIS[j]+1;
                    }
                }
            }
        }
        int ans = LIS[0], ansIndex=0;
        for(int i=0;i<n;i++){
            //ans = Math.max(ans, LIS[i]);
            if(LIS[i] > ans){
                ansIndex = i;
                ans = LIS[i];
            }
        }
        List<Integer> ansArr = new ArrayList<>();
        while(ansIndex != -1){
            ansArr.add(nums[ansIndex]);
            ansIndex = prevIndex[ansIndex];
        }
        Collections.reverse(ansArr);
        ansArr.stream().forEach(ele -> System.out.print(ele + " "));
        return ans;

    //Dry Run
    // [0,1,0,  3,2,3] | 
    //              i            
    //   LIS[1,2,1      3,3,4]
    //prevInd[-1,0,-1,  1,1,4]
    }
}