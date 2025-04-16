/*
Problem: Leetcode 305 - Number of Islands II

Given a 2D grid of `m x n` initialized with water (0), land positions are added one by one. 
After each addLand operation, return the number of islands formed.

Sample Input:
m = 3, n = 3, positions = [[0,0],[0,1],[1,2],[2,1]]
Sample Output:
[1,1,2,3]

Approach 1 (Used Below): Disjoint Set Union (Union-Find)
- Treat each land cell as a node in a graph. Union connected components.
- Time: O(k * α(N*M)) where k = positions.length, α is inverse Ackermann.
- Space: O(N*M)

Other Approaches:
- BFS/DFS: Inefficient for dynamic updates. Needs full re-traversal after each land addition.
*/

// Union-Find / Disjoint Set class with union by size and path compression
class Solution {
    class DisjointSet {
        int[] size, parent;

        DisjointSet(int N) {
            size = new int[N+1]; // Size of each component
            parent = new int[N+1]; // Parent of each node
            for (int i = 0; i <= N; i++) {
                size[i] = 1; // Each component starts with size 1
                parent[i] = i; // Each node is initially its own parent
            }
        }

        int findUltimateParent(int u) {
            if (parent[u] == u) return u; // If node is its own parent, return it
            return parent[u] = findUltimateParent(parent[u]); // Path compression
        }

        void unionBySize(int u, int v) {
            int ulp_u = findUltimateParent(u); // Find parent of u
            int ulp_v = findUltimateParent(v); // Find parent of v
            if (ulp_u == ulp_v) return; // Already in same component

            // Union by size: attach smaller tree under bigger one
            if (size[ulp_u] < size[ulp_v]) {
                size[ulp_v] += size[ulp_u]; // Increase size of v's tree
                parent[ulp_u] = ulp_v; // Point u's tree to v's tree
            } else {
                size[ulp_u] += size[ulp_v]; // Increase size of u's tree
                parent[ulp_v] = ulp_u; // Point v's tree to u's tree
            }
        }
    }

    // Helper to check grid bounds
    boolean isValid(int r, int c, int m, int n) {
        return r >= 0 && r < m && c >= 0 && c < n;
    }

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        boolean[][] seen = new boolean[m][n]; // Tracks which cells are land
        DisjointSet ds = new DisjointSet(m * n); // Disjoint set for all cells
        List<Integer> ans = new ArrayList<>(); // Stores result after each operation
        int count = 0; // Number of islands

        // Directions for 4 neighbors (top, bottom, left, right)
        int[] dirR = {-1, 1, 0, 0};
        int[] dirC = {0, 0, -1, 1};

        for (int[] ele : positions) {
            int r = ele[0], c = ele[1];
            if (seen[r][c]) { // If already land, island count unchanged
                ans.add(count);
                continue;
            }

            seen[r][c] = true; count++; // Mark land and increment island count
            int rcVal = r * n + c; // 1D index of current cell

            for (int i = 0; i < 4; i++) {
                int adjR = r + dirR[i], adjC = c + dirC[i];
                if (isValid(adjR, adjC, m, n) && seen[adjR][adjC]) {
                    int adjVal = adjR * n + adjC; // 1D index of neighbor
                    // If neighbor is land and belongs to different island
                    if (ds.findUltimateParent(rcVal) != ds.findUltimateParent(adjVal)) {
                        ds.unionBySize(rcVal, adjVal); // Merge islands
                        count--; // Merged two islands into one
                    }
                }
            }

            ans.add(count); // Add current island count to result //MISSED THIS 
        }

        return ans;
    }
}
