class Solution1 {
    //Brute Force Approach : Time : O(N log N)
    public int rangeSum_ListBruteForce(int[] nums, int n, int left, int right) {
        left = left-1;
        right = right-1;
        List<Integer> sumList = new ArrayList<>();
        for(int i=0; i<n; i++){//O(N^2)
            int sumitoj = 0;
            for(int j=i; j<n ; j++){
                sumitoj += nums[j];
                sumList.add(sumitoj);
            }
        }
        Collections.sort(sumList); // O (N log N )
        int ans = 0, mod  = (int) 1e9+7;
        for(int i=left; i<=right; i++){
            ans = (ans+sumList.get(i)) % mod;
        }
        return ans;
    }
}


/*
Using Min Heap/Priority Queue
O( R log(N))

// Neetcode :https://www.youtube.com/watch?v=7XTGlO6b16A

*/
class Solution {

   public int rangeSum(int[] nums, int n, int left, int right) {
       int MOD = 1_000_000_007;
       PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->Integer.compare(a[0],b[0]));
       for(int i=0;i<n;i++) pq.add(new int[]{nums[i], i});
       long res=0;
       for(int c=0;c<right;c++){
           int[] cur=pq.poll();
           int sum=cur[0], idx=cur[1];
           if(c>=left-1) res=(res+sum)%MOD;
           if(idx+1<n){
               pq.add(new int[]{sum+nums[idx+1], idx+1});
           }
       }
       return (int)res;
   }
}