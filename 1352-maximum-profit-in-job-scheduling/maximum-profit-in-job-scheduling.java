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
    private static int dfs_binary(int i){
        //base
        if( i==start.length) return 0;
        if( DP[i] != null ) return DP[i];
        //skip job
        int skipAns = dfs(i+1);

        //pick
        int nextJob = Arrays.binarySearch(start, end[i]);
        if(nextJob<0) nextJob = -nextJob -1;        

        int ansTake = dfs(nextJob) + profit[i];
        return DP[i] = Math.max( skipAns, ansTake );
    }
    //Linear Search
    private static int dfs(int i){
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