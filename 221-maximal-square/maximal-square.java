class Solution {
    /*
    Problem: Given a binary matrix A, find the largest square containing only 1s and return its area.
    
    Brute Force Approach:
    - For each cell (i, j) containing '1', check the largest possible square with (i, j) as the top-left corner.
    - Expand the square while ensuring all cells in the new row and column contain '1'.
    - Update the maximum square size found.
    
    Time Complexity: O((mn)^2) - Checking each cell and expanding squares.
    Space Complexity: O(1) - No extra space used.
    */
    public int maximalSquare_bruteForce(char[][] A) {
        int n = A.length, m = (n > 0) ? A[0].length : 0;
        int maxSquareLen = 0; // Stores maximum square side length
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (A[i][j] == '1') { // Start a new square if '1' is found
                    int squareLen = 1; // Initial square size is 1
                    boolean flag = true; // Flag to check validity of square expansion
                    
                    while (squareLen + i < n && squareLen + j < m && flag) {
                        // Check the new row in the square
                        for (int k = j; k <= squareLen + j; ++k) {
                            if (A[i + squareLen][k] == '0') { // If any '0' is found, stop expansion
                                flag = false;
                                break;
                            }
                        }
                        // Check the new column in the square
                        for (int k = i; k < squareLen + i; k++) {
                            if (A[k][j + squareLen] == '0') { // If any '0' is found, stop expansion
                                flag = false;
                                break;
                            }
                        }
                        if (flag) squareLen++; // Expand square if still valid
                    }
                    maxSquareLen = Math.max(maxSquareLen, squareLen); // Update max found
                }
            }
        }
        return maxSquareLen * maxSquareLen; // Return area of largest square
    }

    /*
    Dynamic Programming Approach:
    - Use a DP table where dp[i][j] represents the side length of the largest square ending at (i, j).
    - Transition formula: dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1, if A[i-1][j-1] is '1'.
    - Track the largest square found.
    
    Time Complexity: O(mn) - Single pass through the matrix.
    Space Complexity: O(mn) - Uses an additional DP matrix.
    */
    public int maximalSquare(char[][] A) {
        int n = A.length, m = (n > 0) ? A[0].length : 0;
        int[][] dp = new int[n + 1][m + 1]; // DP table to store square sizes
        int maxSquareLen = 0; // Stores maximum square side length
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (A[i - 1][j - 1] == '1') { // Only process '1' cells
                    // Take the minimum of left, top, and diagonal top-left values
                    int left = dp[i - 1][j];
                    int top = dp[i][j - 1];
                    int diag = dp[i - 1][j - 1];
                    
                    dp[i][j] = Math.min(Math.min(left, top), diag) + 1; // Compute square size
                    maxSquareLen = Math.max(maxSquareLen, dp[i][j]); // Update max found
                }
            }
        }
        return maxSquareLen * maxSquareLen; // Return area of largest square
    }
}
