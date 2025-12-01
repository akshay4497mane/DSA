class Solution {
    /*
    Front Partitioning -> 
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
        DP[N] = 0;
        for(int i=N-1; i>=0; i--){
            int minCost = Integer.MAX_VALUE;
            for(int j=i; j<N; j++){
                if(isPalindrome(i,j,s)){
                    int cost = 1 + DP[j+1];
                    minCost = Math.min(minCost, cost);                    
                }
            }     
            DP[i] = minCost;       
        }
        return DP[0]-1;
    }

    String s; int N=0;
    public int minCut_Memoization(String str) {       
        s = str; N = s.length();
        DP = new int[N+1]; Arrays.fill(DP, -1);
        return helper(0)-1;
    }
    int[] DP;
    int helper(int index){
        if(DP[index]!=-1) return DP[index];
        //1.Base case
        if(index==N) return DP[N] = 0;
        int minCost = Integer.MAX_VALUE;
        //2.Try all possible partitions
        for(int j=index; j<N; j++){
            if( isPalindrome(index, j, s) ){ //palindrome from i..j | call further F(j+1)
                int cost = 1 + helper(j+1);
                minCost = Math.min(minCost, cost);
            }
        }
        return DP[index] = minCost;
    }
    boolean isPalindrome(int i, int j, String str){
        while(i<j){
            if(str.charAt(i) != str.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }
}