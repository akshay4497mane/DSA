/*
import java.util.Queue;
import java.util.ArrayDeque;
import javafx.util.Pair; // Import Pair class (if using JavaFX)
*/
class Solution {

    // Function to calculate the time required to rot all oranges
    public int orangesRotting(int[][] grid) {
        Queue<Pair<Integer, Integer>> rottenQueue = new ArrayDeque<>(); // Queue to track rotten oranges
        int totalRows = grid.length, totalCols = grid[0].length;
        int freshOrangeCount = 0; // Count of fresh oranges

        // Traverse the grid to initialize the queue with rotten oranges and count fresh oranges
        for (int row = 0; row < totalRows; row++) {
            for (int col = 0; col < totalCols; col++) {
                if (grid[row][col] == 2) {
                    rottenQueue.add(new Pair<>(row, col)); // Add rotten orange to queue
                } else if (grid[row][col] == 1) {
                    freshOrangeCount++; // Increment fresh orange count
                }
            }
        }

        // Add a delimiter to mark the end of a minute
        rottenQueue.add(new Pair<>(-1, -1));

        // Directions for moving in 4 adjacent cells
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int elapsedMinutes = -1; // Track the time elapsed

        // Perform BFS
        while (!rottenQueue.isEmpty()) {
            Pair<Integer, Integer> currentCell = rottenQueue.remove();
            int currentRow = currentCell.getKey();
            int currentCol = currentCell.getValue();

            if (currentRow == -1) {
                // End of one minute
                elapsedMinutes++;
                if (!rottenQueue.isEmpty()) {
                    rottenQueue.add(new Pair<>(-1, -1)); // Add another delimiter if more oranges need processing
                }
            } else {
                // Rot all neighboring fresh oranges
                for (int[] direction : directions) {
                    int neighborRow = currentRow + direction[0];
                    int neighborCol = currentCol + direction[1];

                    if (isWithinGridBounds(neighborRow, neighborCol, totalRows, totalCols) &&
                        grid[neighborRow][neighborCol] == 1) {
                        grid[neighborRow][neighborCol] = 2; // Mark orange as rotten
                        freshOrangeCount--; // Decrement fresh orange count
                        rottenQueue.add(new Pair<>(neighborRow, neighborCol)); // Add newly rotten orange to queue
                    }
                }
            }
        }

        // If all fresh oranges are rotten, return the time; otherwise, return -1
        return freshOrangeCount == 0 ? elapsedMinutes : -1;
    }

    // Helper function to check if a cell is within grid bounds
    boolean isWithinGridBounds(int row, int col, int totalRows, int totalCols) {
        return row >= 0 && row < totalRows && col >= 0 && col < totalCols;
    }
}

/* 
Time Complexity: O(n * m) 
- The grid is traversed at most once (O(n * m)), and each cell is processed at most once in the BFS.

Space Complexity: O(n * m)
- In the worst case, the queue can hold all grid cells, requiring O(n * m) space.

Revision Notes:
1. Added meaningful variable names for clarity (`rottenQueue`, `freshOrangeCount`, etc.).
2. Updated comments to explain each step in detail, making the code beginner-friendly.
3. BFS traversal ensures that all neighboring fresh oranges are processed layer by layer.
4. If fresh oranges remain at the end of the BFS, we return `-1`.

Comments:
- `rottenQueue`: Tracks cells with rotten oranges and their processing order.
- `elapsedMinutes`: Tracks the time taken for all oranges to rot.
- Delimiter `(-1, -1)`: Helps separate BFS levels (used to calculate minutes elapsed).
- `directions`: Represents movement in 4 possible directions (up, right, down, left).
- `isWithinGridBounds`: Validates grid boundaries during traversal.

Suggested for Revision:
- Practice using BFS for grid-based problems like island finding or shortest paths.
- Understand how to optimize BFS by avoiding redundant checks for already processed cells.
*/
