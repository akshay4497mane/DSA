class Solution {
        int[][] dir = {{-1, 1, 0, 0 }, 
                       { 0, 0, 1, -1}};
    public int numIslands(char[][] grid) {
        int numOfIslands = 0;
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j]=='1'){
                    dfs(grid, i, j);
                    numOfIslands++;
                }
            }
        }
        return numOfIslands;
    }
    void dfs(char[][] grid, int r, int c){
        //check boundary | 0 condition
        if(r<0 || c<0 || r>=grid.length || c>= grid[0].length || grid[r][c]=='0'){
            return;
        }
        grid[r][c]='0';
        for(int i=0; i<dir[0].length; i++){
            int R = r + dir[0][i];
            int C = c + dir[1][i];
            dfs( grid, R, C);
        }
    }
}