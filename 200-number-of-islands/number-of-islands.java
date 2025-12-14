/*
Problem: Count number of islands in a 2D grid of '1' (land) and '0' (water)

Sample Input: 
grid = {
  {'1','1','0','0','0'},
  {'1','1','0','0','0'},
  {'0','0','1','0','0'},
  {'0','0','0','1','1'}
}
Sample Output: 3
Approach 1: DFS Flood Fill // Time: O(m*n), Space: O(m*n) recursion stack
*/
class Solution {
    public int numIslands(char[][] grid) {
        int ansCount = 0;
        for(int i=0 ;i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j]=='1'){
                    dfs(i,j, grid);
                    ansCount++;
                }
            }
        }
        return ansCount;    
    }
    int[][] directions = {{-1,0},{+1,0}, {0,-1}, {0,+1}};
    void dfs(int i, int j, char[][] grid){
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j]=='0') 
            return;
        System.out.println("dfs("+i + ", " + j + " Setting 1->0");
        grid[i][j] = '0';
        for(int[] dir : directions){
            dfs(i+dir[0], j+dir[1], grid);
        }        
    }
}
/*
dfs(0,0)
[["1","1","1","1","0"],
 ["1","1","0","1","0"],
 ["1","1","0","0","0"],
 ["0","0","0","0","0"]]
*/

class Solution2 {
    private static final int[][] dir = {
        {-1, 1, 0, 0}, // row movement: up, down
        { 0, 0, 1, -1} // col movement: right, left
    };

    public int numIslands(char[][] grid) {
        int islandCount = 0; // number of islands found
        int m = grid.length, n = grid[0].length; // grid dimensions

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') { // unvisited land cell
                    dfs(grid, i, j, m, n); // flood fill the island
                    islandCount++; // increment island count
                }
            }
        }
        return islandCount; // return total islands
    }

    private void dfs(char[][] grid, int r, int c, int m, int n) {
        if (r < 0 || c < 0 || r >= m || c >= n || grid[r][c] != '1') return; // boundary or water check
        grid[r][c] = '0'; // mark cell as visited

        for (int i = 0; i < dir[0].length; i++) {
            int nr = r + dir[0][i]; // next row
            int nc = c + dir[1][i]; // next col
            dfs(grid, nr, nc, m, n); // DFS in that direction
        }
    }
}

/*
Approach 1: DFS Flood Fill using visited[][] | without modifying original array 
// Time: O(m*n), Space: O(m*n) for visited
*/

class Solution_1 {
    private static final int[][] dir = {
        {-1, 1, 0, 0}, // row deltas: up, down
        { 0, 0, 1, -1} // col deltas: right, left
    };

    public int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length; // grid dimensions
        boolean[][] visited = new boolean[m][n]; // to avoid modifying original grid
        int islandCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) { // land not yet visited
                    dfs(grid, visited, i, j, m, n); // flood fill
                    islandCount++; // found one complete island
                }
            }
        }
        return islandCount;
    }

    private void dfs(char[][] grid, boolean[][] visited, int r, int c, int m, int n) {
        if (r < 0 || c < 0 || r >= m || c >= n || visited[r][c] || grid[r][c] == '0') return; // invalid or visited or water
        visited[r][c] = true; // mark cell as visited

        for (int i = 0; i < dir[0].length; i++) {
            int nr = r + dir[0][i]; // next row
            int nc = c + dir[1][i]; // next col
            dfs(grid, visited, nr, nc, m, n); // explore direction
        }
    }
}
