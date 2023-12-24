class Solution {
    int y; //height
    int x; //width
    char[][] g; // grid stored to reduce memory usage(pevents copies during recursion)
    public int numIslands(char[][] grid) {
        g = grid;
        int ans = 0; //count of no if islands
        y = grid.length;  
        if(y == 0 ) return 0;
        x = grid[0].length;
        for(int i=0; i<y ; i++){
            for(int j=0; j<x; j++){
                if(g[i][j] == '1' ){
                    dfs(i,j);
                    ans++;
                }
            }
        }
        return ans;
    }
    public void dfs(int i, int j){
        if(i<0 || j<0 || i>=y || j>=x || g[i][j]!='1') return;
        g[i][j] = '0'; //mark visited
        dfs(i-1, j );
        dfs(i+1, j );
        dfs( i, j-1 );
        dfs( i, j+1 );
    }
}