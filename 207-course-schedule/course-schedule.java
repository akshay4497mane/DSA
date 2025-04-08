class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<numCourses; i++){
            graph.add( new ArrayList<Integer>() );
        }
        int[] indegree = new int[numCourses];
        for(int[] pair : prerequisites){
            graph.get(pair[1]).add(pair[0]);
            indegree[pair[0]]++;
        }

        Queue<Integer> q = new ArrayDeque<>();
        for(int i=0; i<numCourses; i++){
            if(indegree[i] == 0)
                q.add(i);
        }
        if(q.size()==0) return false; //No nodes with indegree=0 => no course to start with

        //Kahn's Algorithm to find cycle
        int nodesVisited = 0;
        while(!q.isEmpty()){
            int node = q.poll();
            nodesVisited++;
            for(int neighbour : graph.get(node)){
                indegree[neighbour]--;
                if(indegree[neighbour] == 0)
                    q.add(neighbour);
            }
        }
        if(nodesVisited == numCourses)
            return true;
        else
            return false; //cycle
    }
}
