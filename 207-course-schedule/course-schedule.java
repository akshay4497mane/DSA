// Problem: Determine if all courses can be finished given prerequisite pairs
// Sample Input: numCourses = 2, prerequisites = [[1,0]]
// Sample Output: true

// Approach: Topological sort using Kahn's Algorithm to detect cycles
// Time Complexity: O(V + E) where V = numCourses, E = prerequisites.length
// Space Complexity: O(V + E)

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>(); // Graph to represent course dependencies

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>()); // Initialize empty adjacency list for each course
        }

        int[] indegree = new int[numCourses]; // Stores number of incoming edges for each course

        for (int[] pair : prerequisites) {
            graph.get(pair[1]).add(pair[0]); // Add an edge from prerequisite to dependent course
            indegree[pair[0]]++; // Increment indegree of the dependent course
        }

        Queue<Integer> q = new ArrayDeque<>(); // Queue for processing nodes with indegree 0

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) q.add(i); // Add course with no prerequisites to queue
        }

        if (q.isEmpty()) return false; // No starting point found, cycle must exist

        int courseNodeVisited = 0; // Count of courses that have been processed

        while (!q.isEmpty()) {
            int node = q.poll(); // Get course with no current prerequisites
            courseNodeVisited++; // Mark course as visited

            for (int neighbour : graph.get(node)) {
                indegree[neighbour]--; // Reduce indegree of dependent course
                if (indegree[neighbour] == 0) q.add(neighbour); // If it has no more prerequisites, add to queue
            }
        }

        return courseNodeVisited == numCourses; // If all courses were visited, no cycle exists
    }
}
