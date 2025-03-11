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

class Solution_Approach1_Recursive_DFS {
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
                    /* Alternative approach:
                    if (!graph.containsKey(i)) {
                        graph.put(i, new ArrayList<>());
                    }
                    graph.get(i).add(j); */
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
/* APPROACH 2  Iterative
1. Model the problem as a directed graph where each bomb is a node.
2. Add an edge from bomb `i` to bomb `j` if `i` can detonate `j`.
3. Perform DFS iteratively from each bomb to count detonations.
4. Return the maximum count.

Time Complexity: 
- O(N²) for building the graph (checking each pair of bombs).
- O(N²) worst-case DFS (visiting all nodes and edges per DFS call).
- Total: O(N³) in the worst case()

Space Complexity:
- O(N²) for the adjacency list (if all bombs are connected).
- O(N) for the DFS visited set.
- Total: O(N²) worst case.
*/

class Solution { // Iterative DFS approach
    public int maximumDetonation(int[][] bombs) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int n = bombs.length;
        
        // Step 1: Build the adjacency list representation of the graph
        for (int i = 0; i < n; i++) { // Bomb i (source)
            for (int j = 0; j < n; j++) { // Bomb j (target)
                if (i == j) continue; // Skip self

                // Extract coordinates and radius
                int xi = bombs[i][0], yi = bombs[i][1], ri = bombs[i][2];
                int xj = bombs[j][0], yj = bombs[j][1];

                // Compute squared Euclidean distance to avoid unnecessary sqrt operation
                long distSquared = (long) (xi - xj) * (xi - xj) + (long) (yi - yj) * (yi - yj);

                // If bomb[i] can reach bomb[j], add an edge i -> j
                if ((long) ri * ri >= distSquared) {
                    graph.computeIfAbsent(i, k -> new ArrayList<>()).add(j);
                }
            }
        }

        // Step 2: Run DFS from each bomb and find the maximum detonations
        int maxDetonations = 0;
        for (int i = 0; i < n; i++) { // O(N) DFS calls
            int count = dfs(i, graph); // Count bombs detonated from i
            maxDetonations = Math.max(maxDetonations, count);
        }
        return maxDetonations;
    }

    // Iterative DFS to count reachable bombs from 'curr'
    private int dfs(int curr, Map<Integer, List<Integer>> graph) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        
        stack.push(curr);
        visited.add(curr); // Mark the starting bomb as visited
        
        while (!stack.isEmpty()) { // O(V + E) per DFS call
            int node = stack.pop(); // Process the current bomb
            
            // Visit all bombs that can be detonated from 'node'
            for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        
        return visited.size(); // Return total number of detonated bombs
    }
}
