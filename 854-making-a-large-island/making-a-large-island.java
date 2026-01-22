
class Solution {

    // Grid size
    int n;

    // Reference to the input grid
    int[][] grid;

    // Directions array to move up, right, down, left
    // Using dirs[d] and dirs[d+1] gives (row, col) movement
    int[] dirs = {0, 1, 0, -1, 0};

    public int largestIsland(int[][] grid) {
        // Store grid reference
        this.grid = grid;
        n = grid.length;

        // Map to store:
        // key   -> unique island id
        // value -> area (number of cells) of that island
        Map<Integer, Integer> islandArea = new HashMap<>();

        // Start labeling islands from 2
        // (0 = water, 1 = unvisited land)
        int islandId = 2;

        // -------------------------------
        // STEP 1: Label each island with a unique id
        //         and compute its area using DFS
        // -------------------------------
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                // Found unvisited land
                if (grid[i][j] == 1) {

                    // Run DFS to mark the island and get its area
                    int area = dfs(i, j, islandId);

                    // Store area for this island id
                    islandArea.put(islandId, area);

                    // Move to next island id
                    islandId++;
                }
            }
        }

        // Initialize answer with max existing island
        int maxArea = 0;
        for (int area : islandArea.values()) {
            maxArea = Math.max(maxArea, area);
        }

        // -------------------------------
        // STEP 2: Try flipping each 0 to 1
        //         and calculate merged island size
        // -------------------------------
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                // Only consider water cells
                if (grid[i][j] == 0) {

                    // Set to avoid counting same island twice
                    Set<Integer> visitedIslands = new HashSet<>();

                    // Area after flipping this cell
                    // Start with 1 for the flipped cell itself
                    int newArea = 1;

                    // Check all 4 neighbors
                    for (int d = 0; d < 4; d++) {

                        int ni = i + dirs[d];
                        int nj = j + dirs[d + 1];

                        // Boundary check
                        if (ni >= 0 && nj >= 0 && ni < n && nj < n) {

                            int neighborId = grid[ni][nj];

                            // Valid neighboring island
                            // island ids are >= 2
                            if (neighborId > 1 && visitedIslands.add(neighborId)) {

                                // Add its area only once
                                newArea += islandArea.get(neighborId);
                            }
                        }
                    }

                    // Update global maximum
                    maxArea = Math.max(maxArea, newArea);
                }
            }
        }

        // If grid has no zero (all 1s), return full grid size
        return maxArea == 0 ? n * n : maxArea;
    }

    // --------------------------------------------------
    // DFS to mark the island with a given id
    // and return the total area of that island
    // --------------------------------------------------
    private int dfs(int i, int j, int id) {

        // Out of bounds or not land
        if (i < 0 || j < 0 || i >= n || j >= n || grid[i][j] != 1) {
            return 0;
        }

        // Mark current cell with island id
        grid[i][j] = id;

        // Area starts with this cell
        int area = 1;

        // Explore all 4 directions
        for (int d = 0; d < 4; d++) {
            area += dfs(i + dirs[d], j + dirs[d + 1], id);
        }

        return area;
    }
}