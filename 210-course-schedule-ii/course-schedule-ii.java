class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {        
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i=0; i<numCourses; i++) //construct graph
            graph.add(new ArrayList<>());
        int[] indegree = new int[numCourses];
        for(int i=0;i<prerequisites.length; i++){
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);//[1] is pre requisite for [0]
            indegree[prerequisites[i][0]]++;
        }
        Queue<Integer> q = new ArrayDeque<>();
        for(int i=0; i<numCourses; i++){
            if( indegree[i] == 0)
                q.add(i);
        }
        int topoLength = 0;
        int[] ans = new int[numCourses]; //Storing Topological Sort Order of Vertices
        while(q.size() > 0 ){
            int rem = q.remove();
            ans[topoLength++] = rem;
            for( int v : graph.get(rem) ){
                indegree[v]--;
                if(indegree[v] == 0)
                    q.add(v);
            }
        }
        if(topoLength == numCourses)
            return ans;
        else
            return new int[0];
    }
}