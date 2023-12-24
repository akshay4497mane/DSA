class Solution {
    int[][] gr;
    int n;
    int[] colour;
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
}