class Solution {
    public int[][] generateMatrix(int n) {
        int val = 1, colMin = 0, colMax = n-1, rowMin=0, rowMax=n-1;
        int[][] ans = new int[n][n];
        while( val <= n*n ){
            for(int c=colMin; c<=colMax; c++)
                ans[rowMin][c] = val++;
            rowMin++; //1 -> 2
            for(int r=rowMin; r<=rowMax; r++)
                ans[r][colMax] = val++;
            colMax--; //1 0 
            for(int c=colMax; c>=colMin; c--)
                ans[rowMax][c] = val++;
            rowMax--; //1 0 
            for(int r=rowMax; r>=rowMin; r--)
                ans[r][colMin] = val++;
            colMin++; //1 -> 2
        }
        return ans;
    }
}