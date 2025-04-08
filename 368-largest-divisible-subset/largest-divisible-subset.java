class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int[] DP = new int[n+1];
        Arrays.fill(DP, 1);
        int[] prevIndex = new int[n+1];
        Arrays.fill(prevIndex, -1);
        for(int i=0; i<n; i++){
            for(int j=0; j<i; j++){
                if( nums[i]%nums[j]==0 || nums[j]%nums[i]==0 ){                    
                    if( DP[j]+1 > DP[i]){
                        DP[i] = DP[j] + 1;
                        prevIndex[i] = j;
                    }              
                }
            }        
        }
        int ans = 0, ansIndex=0;
        for(int i=0;i<n;i++){
            if(DP[i] > ans){
                ans = DP[i];
                ansIndex = i;
            }
        }
        System.out.println("prevIndex : ");
        for( int i=0; i<n ;i++){
            System.out.print(prevIndex[i] + " ");
        }
        System.out.println("DP : ");
        for( int i=0; i<n ;i++){
            System.out.print(DP[i] + " ");
        }

        List<Integer> ansList = new ArrayList<>();
        while(ansIndex != -1){
            ansList.add(nums[ansIndex]);
            ansIndex = prevIndex[ansIndex];
        }
        Collections.reverse(ansList);
        return ansList;
    }
}
