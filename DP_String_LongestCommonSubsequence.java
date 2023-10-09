/*
https://www.interviewbit.com/problems/longest-common-subsequence/
Longest Common Subsequence
Dynamic Programming
Given two strings A and B. Find the longest common sequence ( A sequence which does not need to be contiguous), which is common in both the strings.
You need to return the length of such longest common subsequence.
Problem Constraints
1 <= |A|, |B| <= 1005
Input Format
First argument is an string A.
Second argument is an string B.
Output Format
Return the length of such longest common subsequence between string A and string B.
Example Input
Input 1:
 A = "abbcdgf"
 B = "bbadcgf"
Output 1: 5
Explanation 1: The longest common subsequence is "bbcgf", which has a length of 5
*/
public class Solution {
/*  Brute Force: O(2^N * 2^M)
    Recursion without DP : O(2^ (M+N)) 
    Recursion with DP : O( M * N )
    Loops + DP without Recursion: O(M*N)
*/
    static int[][] ansDP;  //Stores ANS to smaller subProblems
    public int solve(String A, String B) {
        int M = A.length(), N = B.length();
        Solution.ansDP = new int[M+1][N+1]; // M+1 because we need to store [M][N] with 1 based indexing
        return lcsUtilNonRecursiveDP(M,N,A,B); //Non Recursive DP
        /*
        for(int i = 1; i<=M ; i++)
            for(int j=1; j<=N ; j++)//Assigns 0(default) if m,n is 0, -1 for all others,
                Solution.ansDP[i][j] = -1;
        return lcsUtilRecursiveDP( M, N, A, B); //Recursive DP + Memoization
        */
    }
    /*Recursion, Top Down Approach, Break into smaller parts
      Compare last character, Add 1 if matching, 
      subdivide problem, remove last char from 1st OR 2nd or BOTH strings
      Explanation: https://youtu.be/0yvOxPwe3Dg?si=XF-vGovNHIprvqaP
    */
    int lcsUtilRecursiveDP(int m, int n, String A, String B){        
        if( m == 0 || n==0 ) //BASE : if any1 is zero
            return 0;
        if( Solution.ansDP[m][n] != -1 )  //Eliminate REPEAT SOLVING
            return Solution.ansDP[m][n];
        if( A.charAt(m-1) == B.charAt(n-1) ) //When LAST CHAR MATCHES, increase length by 1
            return ( Solution.ansDP[m][n] = 1 + lcsUtilRecursiveDP( m-1 , n-1, A, B) ); //Store, return ans 
        else
           return (Solution.ansDP[m][n] = Math.max(lcsUtilRecursiveDP(m-1,n,A,B), lcsUtilRecursiveDP(m,n-1,A,B)));
    }
    //Looping, Bottom Up Approach, Start from small problems, merge solutions
    //Explanation : https://youtu.be/p09b1IzQa_A?si=yQWcU3Unk2O3RSat
    int lcsUtilNonRecursiveDP(int m, int n, String A, String B){
        for(int i=1; i<=m ; i++)
            for(int j=1; j<=n; j++){
                if( A.charAt(i-1) == B.charAt(j-1) )
                    Solution.ansDP[i][j] = 1 + Solution.ansDP[i-1][j-1];
                else
                    Solution.ansDP[i][j]=Math.max(Solution.ansDP[i-1][j],Solution.ansDP[i][j-1]);
            }
        return Solution.ansDP[m][n];
    }
}
