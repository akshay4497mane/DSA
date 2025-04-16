
class Solution {
    class DisjointSet{
        int[] size, parent; 
        DisjointSet(int N){
            size = new int[N+1];
            parent = new int[N+1];
            for(int i=0; i<=N; i++){
                size[i] = 1;
                parent[i] = i;
            }
        }
        int findUltimateParent(int u){
            if(parent[u] == u) return u;
            return parent[u] = findUltimateParent(parent[u]);
        }
        boolean belongsToSameSet(int u, int v){
            int ulp_u = findUltimateParent(u);
            int ulp_v = findUltimateParent(v);
            if( ulp_u == ulp_v ) 
                return true;
            else
                return false;            
        }
        void unionBySize(int u, int v){ //merge 2 disjoint sets/ merge 2 trees
            int ulp_u = findUltimateParent(u);
            int ulp_v = findUltimateParent(v);
            if( ulp_u == ulp_v ) return;            
            if( size[ulp_u] < size[ulp_v] ){ //move tree u under tree v
                size[ulp_v] = size[ulp_v] + size[ulp_u];
                parent[ulp_u] = ulp_v;
            }else{ //move tree v -> under u
                size[ulp_u] = size[ulp_u] + size[ulp_v];
                parent[ulp_v] = ulp_u;    
            }
        }
    }
    boolean isValid(int r, int c, int m, int n){
        return r>=0 && r<m && c>=0 && c<n;
    }
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        boolean[][] seen = new boolean[m][n];
        DisjointSet ds = new DisjointSet( m * n );
        List<Integer> ans = new ArrayList<>();
        int count=0;
        for( int[] ele : positions){
            int r = ele[0];
            int c = ele[1];
            if(seen[r][c]==true){//1
                ans.add(count);
                continue;
            }
            seen[r][c]=true; count++;
            int[] dirR = new int[]{ -1, 1, 0, 0 };
            int[] dirC = new int[]{ 0, 0, -1, 1 };
            for(int i=0; i<4; i++){
                int adjr = r + dirR[i]; int adjc = c + dirC[i];
                if(isValid(adjr, adjc, m, n)){
                    if(seen[adjr][adjc]==true){
                        int rcValue = r * n + c;
                        int adjrcValue = adjr * n + adjc;
                        if( ds.findUltimateParent(rcValue) != ds.findUltimateParent(adjrcValue)){
                                count--;
                                ds.unionBySize(rcValue, adjrcValue);
                        }
                    }
                }
            }
            ans.add(count);
        }
        return ans;
    }
}