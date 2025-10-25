/*
Problem:
Given two strings str1 and str2, convert str1 into str2 using insert, delete, and replace operations — each costing 1 unit — such that the total cost is minimized.

Sample Input:
str1 = "flower", str2 = "flow"
Output: 2

Intuition:
We use bottom-up DP. dp[i][j] = min cost to convert str1[i..] to str2[j..]. 
Start from the end of both strings and fill DP table backward.

Approaches:
1. Bottom-up DP: Time O(m*n), Space O(m*n)
2. Top-down recursion + memoization: Time O(m*n), Space O(m*n)
*/

class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length(); // Get lengths of both words
        int[][] dp = new int[m+1][n+1]; // dp[i][j] = min cost to convert word1[i..] to word2[j..]

        for (int i = 0; i < m; i++) {
            dp[i][n] = m - i; // If word2 is finished, delete remaining chars from word1
        }
        for (int j = 0; j < n; j++) {
            dp[m][j] = n - j; // If word1 is finished, insert remaining chars from word2
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = 0 + dp[i + 1][j + 1]; // If characters match, move to next pair
                } else {
                    dp[i][j] = 1 + Math.min(
                            Math.min(dp[i + 1][j],    // delete current char from word1
                                    dp[i][j + 1]),   // insert char from word2
                                 dp[i + 1][j + 1]  // replace word1[i] with word2[j]
                    );
                }
            }
        }

        // Print DP matrix to visualize the solution table
        for (int i = 0; i <= m; i++) {
            System.out.println("");
            for (int j = 0; j <= n; j++) {
                System.out.print(dp[i][j] + " ");
            }
        }

        return dp[0][0]; // Minimum cost to convert word1 to word2
    }
}

/*
Neetcode Solution :
https://www.youtube.com/watch?v=XYi2-LPrwm4

i-> word1 : abd
j-> word2 : acd
insert -> 1 + f(i, j+1)
delete -> 1 + f(i+1,j)
replace-> 1 + f(i+1, j+1)

dp[i][j] = min ops to convert word1[i:] to word2[j:]

DP table example:
   r o s ""
h  3 3 4 5 
o  3 2 3 4 
r  2 2 2 3 
s  3 2 1 2 
e  3 2 1 1 
"" 3 2 1 0 
*/
