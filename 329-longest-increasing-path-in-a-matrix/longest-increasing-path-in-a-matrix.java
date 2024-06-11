class Solution {
/*
The code uses a depth-first search (DFS) approach to find the length of the longest increasing path in a given matrix. It employs memoization (DP array) to avoid redundant calculations and explores all four directions from each matrix element, ensuring a strictly increasing path. The time complexity is O(M * N), and the space complexity is O(M * N).   */
    // Directions to move in the matrix (up, right, down, left)
    int[] dir = {0,1,0,-1,0}; // ( i , i+1 )

    public int longestIncreasingPath(int[][] matrix) {
        int M = matrix.length;
        int N = matrix[0].length;
        int[][] DP = new int[M][N]; //Memoizaton, To avoid re computation
        for(int i=0; i< M; i++){ Arrays.fill(DP[i], -1); } //initialize DP -1
        int ans = 0;
        for(int i=0; i< M; i++){
            for(int j=0; j< N; j++){
                ans = Math.max( ans, LIP(matrix, i,j,-1, DP) );
                //LIP() Recursion for every element of array
            }    
        }
        return ans;
    }
    int LIP(int[][] A, int row, int col, int prev, int[][] DP ){// PREV: previous Cell from where call is coming
// Recursive function to calculate the length of increasing path from a given position
        if(! isValid(A, row, col )) return 0; //Check Boundary cases
        if(prev >= A[row][col]) return 0; //we need STRICTLY INCREASING PATH
        if(DP[row][col] == -1 ){//If not yet solved
            int ans = 0;
            for(int i=0; i<4 ; i++){//4 directions : 0,1 | 1,0 | 0,-1 | -1,0
                int x = row + dir[i];
                int y = col + dir[i+1];
                ans = Math.max( ans, LIP(A, x,y, A[row][col], DP) );
            }
            DP[row][col] = 1 + ans; //Add 1 to the path
        } 
        return DP[row][col];
    }
    boolean isValid(int[][] A, int i, int j ){
        if( i<0 || j<0 || i>= A.length || j>=A[0].length ) return false;
        return true;
    }
}