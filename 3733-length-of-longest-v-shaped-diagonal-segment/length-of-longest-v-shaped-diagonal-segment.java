import java.util.*;

class Solution {
    int[][] directions = {{1,1}, {1,-1}, {-1,-1}, {-1,1}};
    int m, n;
    private int[][][][] memo;

    public int lenOfVDiagonal(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        this.memo = new int[m][n][4][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 4; k++) {
                    Arrays.fill(memo[i][j][k], -1);
                }
            }
        }

        int result = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]==1){
                    for(int d = 0; d<4; d++){
                        result = Math.max(result, 1 + solve(i,j,d,true,2,grid));
                    }
                }
            }
        }
        return result;
    }

    int solve(int i, int j, int d, boolean canTurn, int val, int[][] grid){
        int i_ = i + directions[d][0];
        int j_ = j + directions[d][1];
        if(i_ < 0 || i_ >= m || j_ < 0 || j_ >= n || grid[i_][j_] != val)
            return 0;

        int turnInt = canTurn ? 1 : 0;
        if (memo[i_][j_][d][turnInt] != -1) {
            return memo[i_][j_][d][turnInt];
        }
        
        int ans = 0;
        int keepMovingAns = 1 + solve(i_, j_, d, canTurn, val==2?0:2, grid);
        ans = Math.max(ans, keepMovingAns);

        if(canTurn){
            int turnResult = 1 + solve(i_, j_, (d+1)%4, false, val==2?0:2, grid);
            ans = Math.max(ans, turnResult);
        }

        return memo[i_][j_][d][turnInt] = ans;
    }
}
