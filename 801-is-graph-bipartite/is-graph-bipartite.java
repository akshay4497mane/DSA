class Solution {
    int[][] gr;
    int n;
    int[] colour;

/*
    public boolean isBipartite(int[][] graph) {
        gr = graph; n = gr.length; colour = new int[n];
        for(int node=0; node<n;node++){
            if(colour[node]==0 && !validColouringDFS(node, 1))
                return false;
        }
        return true;
    }
    public boolean validColouringDFS(int node, int col){
        if( colour[node]!=0 ) return colour[node] == col;
        colour[node] = col;
        for(int nbr : gr[node]){
            if(!validColouringDFS(nbr, -col))
                return false;
        }
        return true;
    }
*/
//BFS Solution
    public boolean isBipartite(int[][] graph) { 
        gr = graph; n = gr.length; colour = new int[n];
        for(int node=0; node<n;node++){
            if( colour[node]==0 ){
                colour[node] = 1;
                Queue<Integer> q = new ArrayDeque<>();
                q.add(node);
                while(q.size()>0){
                    int rem = q.remove();
                    for(int v : gr[rem]){
                        if(colour[v] !=0 ){
                            if(colour[v] == colour[rem])
                                return false;
                        }else{
                            colour[v] = -1 * colour[rem];
                            q.add(v);
                        }
                    }
                }
            }
        }
        return true;
    }   
}