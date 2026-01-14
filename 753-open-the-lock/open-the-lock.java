//DFS Approach
/* -------------------- Approach 1: DFS (Brute force – NOT optimal) -------------------- */
class Solution_1{
    int ans=Integer.MAX_VALUE;
    Set<String> dead,vis;
    String target;
    public int openLock(String[] deadends,String target){
        this.dead=new HashSet<>(Arrays.asList(deadends));
        this.vis=new HashSet<>();
        this.target=target;
        dfs("0000",0);
        return ans==Integer.MAX_VALUE?-1:ans;
    }
    void dfs(String cur,int steps){
        if(dead.contains(cur)||vis.contains(cur)||steps>=ans) return; // prune
        if(cur.equals(target)){ans=steps; return;} // found target
        vis.add(cur);
        for(String n : next(cur)) 
            dfs(n,steps+1); // try all moves
        vis.remove(cur);
    }

    List<String> next(String s){
        List<String> r=new ArrayList<>(8);
        char[] a=s.toCharArray();
        for(int i=0;i<4;i++){
            char c=a[i];
            a[i]=(c=='9')?'0':(char)(c+1); r.add(new String(a)); // rotate up
            a[i]=(c=='0')?'9':(char)(c-1); r.add(new String(a)); // rotate down
            a[i]=c;
        }
        return r;
    }
}

/* -------------------- Approach 2: BFS (Expected & Correct) -------------------- */
class Solution{
    public int openLock(String[] deadends,String target){
        Set<String> dead=new HashSet<>(Arrays.asList(deadends));
        if(dead.contains("0000")) return -1; // blocked at start
        Queue<String> q=new ArrayDeque<>();
        Set<String> vis=new HashSet<>();
        q.offer("0000"); vis.add("0000");
        int steps=0;
        while(!q.isEmpty()){
            for(int sz=q.size();sz>0;sz--){
                String cur=q.poll();
                if(cur.equals(target)) return steps; // shortest path
                for(String n:next(cur)){
                    if(!dead.contains(n)&&vis.add(n)) q.offer(n); // valid next
                }
            }
            steps++;
        }
        return -1;
    }

    List<String> next(String s){
        List<String> r=new ArrayList<>(8);
        char[] a=s.toCharArray();
        for(int i=0;i<4;i++){
            char c=a[i];
            a[i]=(c=='9')?'0':(char)(c+1); r.add(new String(a));
            a[i]=(c=='0')?'9':(char)(c-1); r.add(new String(a));
            a[i]=c;
        }
        return r;
    }
}