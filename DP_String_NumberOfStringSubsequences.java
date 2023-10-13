package DSAPackage;
/*
Asked in Salesforce OA
Find number of times a string occurs as a subsequence in given string
https://www.geeksforgeeks.org/find-number-times-string-occurs-given-string/
Given two strings, find the number of times the second string occurs in the first string, whether continuous or discontinuous.
Examples: 
Input:  
string a = "GeeksforGeeks"
string b = "Gks"
Output: 4
*/
public class SalesforcePractice {
	public static void main(String[] args) {
		String s1 = "Gke";
		String s2 = "GekGeke";
		int m = s1.length(), n = s2.length();
		S.dp = new long[m+1][n+1];
		
//		for(int i = 0; i<= m; i++)
//			for(int j=0;j<=n ;j++)
//				S.dp[i][j] = -1;
//		System.out.println("Count of String s1:" + s1 + " in S2:" +s2+ " is : " + S.count(s1, s2, m, n) );
		for(int i = 0; i<= m; i++)
			S.dp[i][0] = 0;
		for(int j=0; j<=n ;j++)
			S.dp[0][j] = 1;
		System.out.println("Count of String s1:" + s1 + " in S2:" +s2+ " is : " + S.countBottomUp(s1, s2, m, n) );
	}
}

class S{
	static long[][] dp;
	
	static long count( String a, String b, int m, int n ) { //Top Down Recursive DP Approach
		//System.out.println("Count(" + a +"," + b + " " + m +" "+n +")");
		if( m==0 || (m==0 && n==0))
			return 1;
		if(n==0) //ABC, ""
			return 0;

		if(S.dp[m][n] != -1)
			return S.dp[m][n];
		
		if(a.charAt(m-1) == b.charAt(n-1))
			return S.dp[m][n] = S.count(a, b, m-1, n-1) + S.count(a, b, m, n-1);
		else
			return S.dp[m][n] = S.count(a, b, m, n-1);		
	}
	static long countBottomUp(String a, String b, int m, int n) { //Bottom Up DP
		for(int i =1 ; i<=m ; i++) {
			for(int j =1; j<=n ; j++) {
				if( a.charAt(i-1) == b.charAt(j-1) ) {
					 // If last characters are same, we have two options - 
					//1. consider last characters of both strings in solution
		            // 2. ignore last character of first string					
					S.dp[i][j] = S.dp[i-1][j-1] + S.dp[i][j-1];
				}else {
				    // If last character are different, ignore last character of first string
					S.dp[i][j] = S.dp[i][j-1];
				}
			}
		}
		return S.dp[m][n];
	}
}
