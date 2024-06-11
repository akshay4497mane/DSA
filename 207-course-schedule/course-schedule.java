class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //construct graph  bi->ai
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for(int i=0; i < numCourses; i++){
            graph.add( new ArrayList<>() );
        }
        int[] indegree = new int[numCourses]; //IN DEGREE for all Nodes
        for(int i=0; i < prerequisites.length; i++){
            int v = prerequisites[i][0];
            int u = prerequisites[i][1];
            graph.get(u).add(v); // u -> v   [u is prerequisite for v]
            indegree[v]++; //Pre Calculating IN DEGREE while constructing Graph
        }
        //find cycle | KAHNS ALGORITHM using indegree[]
        int topoLength = 0; //Topological Sort : v1 -> v2 -> v3...
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i < numCourses; i++){
            if(indegree[i] == 0)
                q.add( i );
        }
        while( q.size() > 0 ){
            int rem = q.remove();
            topoLength++;
            for ( int v : graph.get(rem)){
                indegree[v]--;
                if( indegree[v] == 0 )
                    q.add(v);
            }
        }
        if( topoLength == numCourses )//no Cycle
            return true;
        else
            return false;
    }
}
//50:31