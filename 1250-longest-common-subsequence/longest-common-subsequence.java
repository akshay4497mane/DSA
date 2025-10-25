class Solution {
    public int longestCommonSubsequence(String s1, String s2) {
        int n1 = s1.length(), n2=s2.length();
        int[][] cache = new int[n1+1][n2+1];
        for(int i=n1-1; i>=0; i--){
            for(int j=n2-1; j>=0; j--){
                if(s1.charAt(i) == s2.charAt(j)){
                    cache[i][j] = 1 + cache[i+1][j+1];
                }else{//Not Equal
                    cache[i][j] = Math.max( cache[i+1][j], cache[i][j+1] );
                }
            }
        }
        /*
        for(int i=0; i<n1; i++){
            System.out.println("");
            for(int j=0; j<n2; j++){
                System.out.print(cache[i][j] + " ");
            }
        }
        */
        return cache[0][0];
    }
}

/*
Approach: Solve using Bottom up DP. 
Neetcode Video: https://www.youtube.com/watch?v=Ua0GhsJSlWM
cache[i][j] = LCS between s1[i..] & s2[j..]

if s1[i] == s2[j]
   1 + cache(i+1, j+1)
else if not equal
   max ( cache(i+1,j) , cache(i, j+1) )

s1 : no of rows
s2 : no of cols
   a c e ''
a  3 2 1 0
b  2 2 1 0
c  2 2 1 0
d  1 1 1 0
e  1 1 1 0
'' 0 0 0 0
*/