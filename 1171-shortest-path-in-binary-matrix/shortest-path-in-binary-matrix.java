/*
Problem Statement:
Given an n x n binary matrix 'grid' where 0 represents an empty cell and 1 represents an obstacle,
find the shortest path from the top-left corner (0,0) to the bottom-right corner (n-1, n-1).
You can move in 8 possible directions, and a path can only pass through empty cells (0).
Return the shortest path length, or -1 if no such path exists.

Approach: ( by overriding values in matrix)
- Use Breadth-First Search (BFS) since it guarantees the shortest path in an unweighted graph.
- Start from (0,0) and mark it as visited by changing its value to the current path length.
- Use a queue to explore all possible neighbours/moves in 8 directions.
- If the destination (n-1, n-1) is reached, return the distance.
- Otherwise, continue BFS until the queue is empty | keep adding 1 to every distance of every neighbour and update value in grid
- If no path exists, return -1.

Time Complexity: O(N^2) (Each cell is visited at most once)
Space Complexity: O(N^2) (Worst case: entire grid is stored in the queue)

*/

import java.util.*;

class Solution {
    // 8 possible directions of movement (up, down, left, right, and diagonals)
    private static final int[][] directions = new int[][]{
        {-1, -1}, { 0, -1}, { 1, -1}, 
        {-1,  0},           { 1,  0}, 
        {-1,  1}, { 0,  1}, { 1,  1}
    };

    public int shortestPathBinaryMatrix(int[][] grid) {
        int M = grid.length, N = grid[0].length;

        // If start or end cell is blocked, return -1
        if (grid[0][0] != 0 || grid[M-1][N-1] != 0) 
            return -1;

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0});
        grid[0][0] = 1; // Mark as visited with distance 1

        while (!q.isEmpty()) {
            int[] cell = q.remove();
            int r = cell[0], c = cell[1];
            int currDistanceFromStart = grid[r][c];

            // If we reach the bottom-right cell, return the path length
            if (r == M - 1 && c == N - 1) 
                return currDistanceFromStart;

            // Explore all 8 possible movements
            for (int[] dir : directions) {
                int nr = r + dir[0]; // New row
                int nc = c + dir[1]; // New column

                // Check if the new position is within bounds and not visited
                if (nr >= 0 && nc >= 0 && nr < M && nc < N && grid[nr][nc] == 0) {
                    q.add(new int[]{nr, nc});
                    grid[nr][nc] = currDistanceFromStart + 1; // Update distance
                }
            }
        }

        // No valid path found
        return -1;
    }
}
