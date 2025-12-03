class Solution {
    /*
    1. Build Graph adjacency List
    2. build indegree[] array for each v
    3. Collect vertex where indegree is 0 all where 
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
        int[] indegree = new int[numCourses];
        for(int i=0; i<numCourses; i++)
            graph.put(i, new ArrayList<Integer>());
        for( int[] perq : prerequisites ){
            graph.computeIfAbsent(perq[1], (k) -> new ArrayList<Integer>()).add(perq[0]);
            indegree[perq[0]]++;
        }

        for(int i=0; i<numCourses; i++){
            //System.out.println(i + ": No of edges : " + graph.get(i).size());
            System.out.println(i + ": indegree: " + indegree[i]);
        }

        Queue<Integer> q = new ArrayDeque<>();
        for(int i=0; i<numCourses; i++) //collect all where indegree=0
            if(indegree[i]==0)
                q.add(i);
        List<Integer> ans = new ArrayList<>();
        while(!q.isEmpty()){
            int currV = q.remove();
            ans.add(currV);
            for(int neighbor : graph.get(currV)){
                if(--indegree[neighbor] == 0)
                    q.add(neighbor);
            }
        }
        for(int i=0; i<numCourses; i++) //collect all where indegree=0
            if(indegree[i]!=0)
                return new int[]{};
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}