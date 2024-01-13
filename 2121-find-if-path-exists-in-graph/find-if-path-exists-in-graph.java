class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        List<List<Integer>> graph = buildGraph(n, edges);
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();

        if(source == destination) return true;
        q.add(source);
        visited[source] = true;
        while(!q.isEmpty()){
            int curr = q.poll();
            for(Integer v : graph.get(curr)){ //for all neighbours of curr
                if(!visited[v]){
                    if(v == destination ) return true;
                    visited[v] = true;
                    q.add(v);
                }
            }
        }
        return false;
    }
    List<List<Integer>> buildGraph(int n, int[][] edges){
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0; i<n ;i++) 
            graph.add( new ArrayList<>() );
        for(int[] edge : edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }
    void printGraph(List<List<Integer>> graph){
        int i=0;
        for(List<Integer> gr : graph){
            System.out.print(i++ + " : ");
            for(Integer e : gr)
                System.out.print(" " + e);
            System.out.println("");
        }

    }


}