class Solution {
    /*
    Approach : Front Partitioning, for every index i =0..N-1 -> partition at i | recursive call further
    f(i) -> 
      base case : i==n : return 0
      for j=i-n-1
        cost = 1+f(i+1)
        maintain minCost
    1. Recursive
    2. Memoization
    3. Tabulation
    */
    public int minCut(String s) {//Approach 3 : Tabulation       
        int N = s.length();
        DP = new int[N+1]; 
        memoPalindrome = new Boolean[s.length()][s.length()];
        DP[N] = 0;
        for(int i=N-1; i>=0; i--){
            int minCost = Integer.MAX_VALUE;
            for(int j=i; j<N; j++){
                if( isPalindrome(i,j,s) ){
                    int cost = 1 + DP[j+1];
                    minCost = Math.min(minCost, cost);                    
                }
            }     
            DP[i] = minCost;       
        }
        return DP[0]-1;
    }

    String s; int N=0;
    public int minCut_Memoization(String str) {    // Approach 1 : Recursive | Approach 2 : Memoization
        s = str; N = s.length();
        DP = new int[N+1]; Arrays.fill(DP, -1);
        memoPalindrome = new Boolean[s.length()][s.length()];
        return helper(0)-1;
    }
    int[] DP;
    int helper(int index){
        if(DP[index]!=-1) return DP[index];
        if(index==N) return DP[N] = 0; // 1.Base case
        int minCost = Integer.MAX_VALUE;
        for(int j=index; j<N; j++){ // 2.Try all possible partitions
            if( isPalindrome(index, j, s) ){ //palindrome from i..j | call further F(j+1)
                int cost = 1 + helper(j+1);
                minCost = Math.min(minCost, cost);
            }
        }
        return DP[index] = minCost;
    }
    private Boolean memoPalindrome[][];
    private boolean isPalindrome( int start, int end, String s ) {
        if (start >= end) return true;
        if (memoPalindrome[start][end] != null) return memoPalindrome[start][end];
        return ( memoPalindrome[start][end] = (s.charAt(start) == s.charAt(end)) && isPalindrome(start + 1, end - 1, s) );
    }

    boolean isPalindrome_OLD(int i, int j, String str){
        while(i<j){
            if(str.charAt(i) != str.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
}