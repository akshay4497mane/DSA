class Solution {
    static Integer[] DP;
    static int[] start, end, profit;
    public int jobScheduling(int[] s, int[] e, int[] p) {
        int n = s.length;
        Integer[] idx = new Integer[n];
        for(int i=0; i<n; i++) idx[i] = i;            
        Arrays.sort(idx, (a,b) -> s[a] - s[b] );
        start = new int[n];
        end = new int[n];
        profit = new int[n];
        for(int i=0; i<n; i++){
            start[i] = s[ idx[i] ];
            end[i] = e[ idx[i] ];
            profit[i] = p[ idx[i] ];
        }
        DP = new Integer[n];        
        return dfs(0);                
    }
    //Binary Search
    private static int dfs(int i){
        //base
        if( i==start.length) return 0;
        if( DP[i] != null ) return DP[i];
        //skip job
        int skipAns = dfs(i+1);

        //pick/take the job
        int nextJob = nextIndex(i);
        //int nextJob = Arrays.binarySearch(start, end[i]); //WRONG
        //int nextJob = Arrays.binarySearch(start, i + 1, start.length, end[i]);//Failed for [1,2,2,3]
        //if (nextJob < 0) nextJob = -nextJob - 1;
        // nextJob is guaranteed >= i+1
        int ansTake = dfs(nextJob) + profit[i];
        return DP[i] = Math.max( skipAns, ansTake );
    }
    private static int nextIndex(int i) {
        int lo = i + 1, hi = start.length;
        int target = end[i];

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (start[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    //Linear Search
    /* 
Runtime 906 ms Beats 9.34%
Memory 74.64 MB Beats 30.94%
    */
    private static int dfs_linear(int i){
        //base
        if( i==start.length) return 0;
        if( DP[i] != null ) return DP[i];
        //skip job
        int skipAns = dfs(i+1);

        //pick
        int nextJob = i+1;
        while( nextJob<start.length && start[nextJob] < end[i] ){
            nextJob++;
        }
        int ansTake = dfs(nextJob) + profit[i];
        return DP[i] = Math.max( skipAns, ansTake );
    }
}