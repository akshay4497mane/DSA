class Solution {
/*
Approach 1: Brute force DFS (forward)
Summary: Try all possible sequences starting from (sx,sy). Stop when exceeding target. This demonstrates feasibility but explodes exponentially.
Time: Exponential
Space: Exponential (recursion stack)
*/
    public boolean reachingPoints_1(int sx,int sy,int tx,int ty){
        return dfs(sx,sy,tx,ty);
    }
    boolean dfs(int x,int y,int tx,int ty){
        if(x>tx||y>ty) return false;
        if(x==tx&&y==ty) return true;
        return dfs(x,x+y,tx,ty)||dfs(x+y,y,tx,ty);
    }
/*
Approach 2: BFS (forward)
Summary: Level-order traversal avoids deep recursion but still explores enormous state space. Slightly better control, still TLE.
Time: Exponential
Space: Exponential (queue + visited)
*/
    public boolean reachingPoints_2(int sx,int sy,int tx,int ty){
        Queue<int[]> q=new LinkedList<>();
        q.add(new int[]{sx,sy});
        while(!q.isEmpty()){
            int[] p=q.poll();
            int x=p[0],y=p[1];
            if(x>tx||y>ty) continue;
            if(x==tx&&y==ty) return true;
            q.add(new int[]{x,x+y});
            q.add(new int[]{x+y,y});
        }
        return false;
    }
/*
Approach 3: Reverse greedy with subtraction
Summary: Work backwards from (tx,ty). Since forward only adds, reverse only subtracts the smaller from the larger. This mirrors GCD logic.
Time: O(tx+ty) worst-case
Space: O(1)
*/
    //WRONG
    public boolean reachingPoints_3(int sx,int sy,int tx,int ty){
        while( tx>sx && ty>sy ){
            if(tx>ty) 
                tx-=ty;
            else 
                ty-=tx;
        }
        return (tx==sx&&ty==sy);
    }
    public boolean reachingPoints(int sx,int sy,int tx,int ty){
        while(tx>=sx&&ty>=sy){
            if(tx==sx&&ty==sy) return true;
            if(tx>ty) tx-=ty;
            else ty-=tx;
            if(tx==sx) return (ty-sy)%sx==0;
            if(ty==sy) return (tx-sx)%sy==0;
        }
        return false;
    }    

}


/*

      1-1
   2-1     1-2
  3-1,2-3    32,   13
41,34 53,25 52,35  43,1,

*/