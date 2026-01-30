/*
Problem summary (3–5 bullets)
• You are given an m×n grid where 1 represents land and 0 represents sea
• You can move between land cells in 4 directions (up, down, left, right)
• You can also walk off the grid boundary from boundary land cells
• A land cell is an enclave if it is **impossible to reach the boundary** from it
• Return the total number of such land cells

Constraints & edge cases
• 1 ≤ m, n ≤ 500
• Grid may have all water or all land
• Land connected to boundary (directly or indirectly) is NOT an enclave
• Single row / single column grids → no enclaves possible
• Must avoid revisiting cells (cycles)

====================================================
Approach 1: Brute Force (DFS from every land cell)
==================================================

Detailed intuition and thought process
Beginner thinking:
“I’ll start from each land cell and try all possible paths.
If I can reach outside the grid, then it’s not an enclave.”

Why it seems correct
The definition literally says “cannot walk off the boundary”.

What limitation appears
Same regions are explored repeatedly.
DFS from each land cell re-traverses the same connected components.

Key insight leading to next approach
We should **not recompute reachability for every cell**.
Instead, mark land that can already escape.

Plain-English algorithm
• For every land cell, run DFS
• If DFS touches boundary, mark as non-enclave
• Count cells that never reach boundary

Time & space complexity derivation
• DFS can visit O(m*n) cells
• Done for each land cell → O((m*n)²) → TLE
• Space: O(m*n) recursion stack

Java code (Brute Force – intentionally inefficient)
*/

class Solution1 {

    int rows, cols;
    int[][] grid;
    boolean canEscape;

    public int numEnclaves(int[][] grid) {
        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;
        int count = 0;

        // Try DFS from every land cell
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    canEscape = false;
                    boolean[][] visited = new boolean[rows][cols];
                    dfs(i, j, visited);

                    // If cannot escape, this land cell is enclave
                    if (!canEscape) count++;
                }
            }
        }
        return count;
    }

    private void dfs(int r, int c, boolean[][] visited) {
        // Walking off boundary
        if (r < 0 || c < 0 || r >= rows || c >= cols) {
            canEscape = true;
            return;
        }

        // Stop on water or visited
        if (grid[r][c] == 0 || visited[r][c]) return;

        visited[r][c] = true;

        // Explore all 4 directions
        dfs(r + 1, c, visited);
        dfs(r - 1, c, visited);
        dfs(r, c + 1, visited);
        dfs(r, c - 1, visited);
    }
}
/*
Cons
• Massive redundant DFS calls
• Guaranteed TLE for large grids
• Not scalable

====================================================
Approach 2: Better (DFS from boundary land cells)
=================================================

Why optimization is required
Instead of checking who **cannot escape**,
mark all land cells that **can escape**.

Detailed intuition and thought process
Beginner realization:
“If a land cell is connected to boundary land, it can escape.”
So remove all boundary-connected land first.

Key insight
Enclaves = total land − land connected to boundary.

Plain-English algorithm
• Start DFS from all boundary land cells
• Mark all reachable land as visited (escapable)
• Count unvisited land cells

Time & space complexity derivation
• Each cell visited once → O(m*n)
• Space: O(m*n) recursion stack

Java code (DFS from boundary)

*/
class Solution { //approach2

    int rows, cols;
    int[][] grid;

    public int numEnclaves(int[][] grid) {
        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;

        // DFS from all boundary land cells
        for (int i = 0; i < rows; i++) {
            dfs(i, 0);
            dfs(i, cols - 1);
        }
        for (int j = 0; j < cols; j++) {
            dfs(0, j);
            dfs(rows - 1, j);
        }

        int count = 0;

        // Count remaining land (enclaves)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) count++;
            }
        }
        return count;
    }

    private void dfs(int r, int c) {
        // Stop on boundary overflow
        if (r < 0 || c < 0 || r >= rows || c >= cols) return;

        // Stop on water or already visited
        if (grid[r][c] == 0) return;

        // Mark as visited (remove land)
        grid[r][c] = 0;

        // Explore neighbors
        dfs(r + 1, c);
        dfs(r - 1, c);
        dfs(r, c + 1);
        dfs(r, c - 1);
    }
}
/*

Cons
• Recursive DFS risks stack overflow
• Mutates input grid (sometimes undesirable)

====================================================
Final Approach: Optimal (BFS from boundary – safest)
====================================================

Detailed intuition and thought process
Same logic as DFS, but BFS avoids recursion risk.
Safest and most interview-preferred.

Plain-English algorithm
• Push all boundary land cells into queue
• BFS and mark all reachable land as water
• Count remaining land

Time & space complexity derivation
• Each cell processed once → O(m*n)
• Queue + grid storage → O(m*n)

Java code (Optimal – BFS)

*/
class Solution3 {
    public int numEnclaves(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();

        // Add all boundary land cells
        for (int i = 0; i < rows; i++) {
            if (grid[i][0] == 1) queue.offer(new int[]{i, 0});
            if (grid[i][cols - 1] == 1) queue.offer(new int[]{i, cols - 1});
        }
        for (int j = 0; j < cols; j++) {
            if (grid[0][j] == 1) queue.offer(new int[]{0, j});
            if (grid[rows - 1][j] == 1) queue.offer(new int[]{rows - 1, j});
        }

        // BFS to remove all escapable land
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0];
            int c = cell[1];

            if (r < 0 || c < 0 || r >= rows || c >= cols) continue;
            if (grid[r][c] == 0) continue;

            // Mark land as water (visited)
            grid[r][c] = 0;

            for (int[] d : dirs) {
                queue.offer(new int[]{r + d[0], c + d[1]});
            }
        }

        // Count enclaves
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) count++;
            }
        }
        return count;
    }
}
/*

Dry run (optimal approach)
Grid:
0 0 0 0
1 0 1 0
0 1 1 0
0 0 0 0

Boundary BFS removes land at (1,0).
Remaining land = (1,2), (2,1), (2,2) → 3 enclaves.

30–40 second interview explanation
“I first tried checking escape paths from every land cell, but that repeats work and TLEs.
The key insight is that any land connected to the boundary is NOT an enclave.
So I BFS from boundary land cells and remove all escapable land.
The remaining land count is the answer in O(m*n) time.”

Source
LeetCode: [https://leetcode.com/problems/number-of-enclaves/](https://leetcode.com/problems/number-of-enclaves/)
GFG: [https://practice.geeksforgeeks.org/problems/number-of-enclaves/1](https://practice.geeksforgeeks.org/problems/number-of-enclaves/1)
*/