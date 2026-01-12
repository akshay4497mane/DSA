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
    //WRONG ans
    public boolean reachingPoints_3(int sx,int sy,int tx,int ty){
        while( tx>sx && ty>sy ){
            if(tx>ty) 
                tx-=ty;
            else 
                ty-=tx;
        }
        return (tx==sx&&ty==sy);
    }
    //WORKS correctly
    public boolean reachingPoints_32(int sx,int sy,int tx,int ty){
        while(tx>=sx&&ty>=sy){
            if(tx==sx&&ty==sy) return true;
            if(tx>ty) tx-=ty;
            else ty-=tx;
            if(tx==sx) return (ty-sy)%sx==0;
            if(ty==sy) return (tx-sx)%sy==0;
        }
        return false;
    }    
/*
Approach 4: Optimized reverse using modulo (final)
Summary: Instead of repeated subtraction, jump using modulo. When one coordinate matches start, directly validate the other via divisibility. This is optimal.
Time: O(log(max(tx,ty)))
Space: O(1)
*/
    public boolean reachingPoints(int sx,int sy,int tx,int ty){
        while(tx>=sx && ty>=sy){
            if(tx==sx && ty==sy) return true;
            if(tx>ty){
                if(ty==sy) return (tx-sx)%ty==0;
                tx%=ty;
            }else{
                if(tx==sx) return (ty-sy)%tx==0;
                ty%=tx;
            }
        }
        return false;
    }

/*
Why? explain? if(ty==sy) return (tx-sx)%ty==0;

• y is already fixed at the start value
• Only x needs to be reduced to sx
• We are going backwards

What forward moves look like when y is fixed
If y = sy is constant, the only valid forward move is:
(x, sy) → (x + sy, sy)

So forward, x can only increase by exactly sy each step:
sx, sx+sy, sx+2sy, sx+3sy, ...

What backward must check
We are at (tx, sy) and want to reach (sx, sy).
Backward move is:
(tx, sy) → (tx − sy, sy)

So the question becomes:
Can tx reach sx by repeatedly subtracting sy?

That is pure arithmetic:
tx = sx + k * sy for some integer k ≥ 0

Rewritten:
(tx − sx) % sy == 0
*/

/*
Approach 3 intuition: Reverse greedy with subtraction
Think backward instead of forward. Forward moves only add, so backward moves must subtract. From (tx, ty), the last move must have come from either (tx−ty, ty) or (tx, ty−tx). So at each step, subtract the smaller value from the larger. This mimics undoing one forward operation at a time. When one coordinate hits the start value, the other must be reachable by repeated additions of that same value (divisibility check).

Why it’s limited
This is logically correct but inefficient. In worst cases like (1,1) → (1e9,1), you subtract 1 repeatedly → too slow.

Approach 4 intuition: Reverse greedy with modulo (Euclid jump)
Approach 4 is the same logic as Approach 3, but done in bulk. Instead of subtracting the smaller number one-by-one, subtract it as many times as possible in a single step using modulo. This is exactly Euclid’s algorithm for GCD. Once one coordinate equals the start, you only need to check if the remaining difference can be closed by repeated additions of the fixed value.

Key mental model (interview gold)
Approach 3 = simulate undoing moves step-by-step
Approach 4 = mathematically compress those steps using modulo

Both rely on the same invariant: at any point, the pair (x,y) must be reachable via repeated additions of the smaller number. The modulo version just reaches the answer before time runs out.
*/
}


/*

      1-1
   2-1     1-2
  3-1,2-3    32,   13
41,34 53,25 52,35  43,1,

*/