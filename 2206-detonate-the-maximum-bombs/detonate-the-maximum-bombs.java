/*
Problem: Given a list of bombs with (x, y, radius), determine the maximum number of bombs that can be detonated starting from any single bomb.

Approach:
1. Graph Construction:  
   - Treat each bomb as a node.  
   - Create a directed edge from bomb i → j if bomb j lies within the radius of bomb i.  
   - This forms an adjacency list representation of a directed graph.

2. DFS Traversal:  
   - Start DFS from each bomb to count how many bombs can be detonated.  
   - Track visited nodes to avoid re-exploring.  
   - Store max detonations across all DFS runs.

Time Complexity:
- Graph Construction: O(N²) (since we compare each pair of bombs).  
- DFS Traversal: Each DFS runs in O(V + E), where V = N (bombs) and E = O(N²) in the worst case.  
- Overall Complexity: O(N³) (running DFS from each bomb).  

Space Complexity:
- Graph Storage: O(N²) (worst case when all bombs are within reach of each other).  
- DFS Visited Set: O(N).  
- Overall Space Complexity: O(N²).
*/

class Solution {
    public int maximumDetonation(int[][] bombs) {
        Map<Integer, List<Integer>> graph = new HashMap<>(); // Adjacency list graph representation
        int n = bombs.length;

        // Step 1: Build the graph where an edge exists if a bomb can trigger another
        for (int i = 0; i < n; i++) { // Bomb i (source)
            for (int j = 0; j < n; j++) { // Bomb j (target)
                if (i == j) continue; // Skip self

                int xi = bombs[i][0], yi = bombs[i][1], ri = bombs[i][2];
                int xj = bombs[j][0], yj = bombs[j][1];

                // Compute squared Euclidean distance to avoid unnecessary sqrt computation
                long distij_square = (long) (xi - xj) * (xi - xj) + (long) (yi - yj) * (yi - yj);
                if ((long) ri * ri >= distij_square) { // If j is within i's radius, add edge i → j
                    graph.computeIfAbsent(i, k -> new ArrayList<>()).add(j);  
                    // computeIfAbsent ensures key exists; avoids null checks
                }
            }
        }

        // Step 2: Perform DFS from each bomb and track max detonation count
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int count = dfs(i, new HashSet<>(), graph); // Start DFS from each bomb
            ans = Math.max(ans, count);
        }
        return ans;
    }

    /*
    DFS to count reachable bombs from the current bomb
    - visited: Tracks already-explored bombs to avoid cycles.
    - graph: Passed explicitly to keep method pure (no reliance on global state).
    */
    private int dfs(int curr, Set<Integer> visited, Map<Integer, List<Integer>> graph) {
        visited.add(curr); // Mark bomb as visited
        int count = 1; // Include the current bomb in count

        for (int neighbour : graph.getOrDefault(curr, new ArrayList<>())) {  
            // getOrDefault ensures we don't get null for bombs with no outgoing edges
            if (!visited.contains(neighbour))  
                count += dfs(neighbour, visited, graph);  
        }
        return count;
    }
}
