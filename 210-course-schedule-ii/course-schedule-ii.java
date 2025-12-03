class Solution {
    /*
     * Kahn’s Algorithm for Topological Sorting (BFS on DAG
     * Goal:
     * Return a valid course order such that all prerequisites are satisfied.
     * If a cycle exists, return empty array.
     * Steps:
     * 1. Build adjacency list (prereq -> dependent courses)
     * 2. Build indegree array for each course
     * 3. Push all zero-indegree courses into queue
     * 4. Process queue using BFS and reduce indegrees
     * 5. If all nodes are processed → valid order, else cycle exists
     * Time Complexity:  O(V + E)
     * Space Complexity: O(V + E)
     */

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Adjacency list: course -> list of next dependent courses
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        // indegree[i] = number of prerequisites for course i
        int[] indegree = new int[numCourses];

        // Initialize adjacency list for all courses
        for (int i = 0; i < numCourses; i++)
            adjacencyList.put(i, new ArrayList<>());
        for (int[] edge : prerequisites) { // Build graph and indegree array
            int course = edge[0];
            int prerequisite = edge[1];
            adjacencyList.get(prerequisite).add(course);
            indegree[course]++;
        }
        // Queue to process courses with zero prerequisites
        Queue<Integer> queue = new ArrayDeque<>();
        for (int course = 0; course < numCourses; course++) {
            if (indegree[course] == 0) {
                queue.offer(course);
            }
        }
        List<Integer> topoOrder = new ArrayList<>();
        // BFS Topological Sort
        while (!queue.isEmpty()) {
            int currentCourse = queue.poll();
            topoOrder.add(currentCourse);
            // Reduce indegree of dependent courses
            for (int nextCourse : adjacencyList.get(currentCourse)) {//neighbour
                if (--indegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }
        // If not all courses are processed, a cycle exists
        if (topoOrder.size() != numCourses) {
            return new int[0];
        }
        // Convert List<Integer> → int[]
        return topoOrder.stream().mapToInt(Integer::intValue).toArray();
    }
}
