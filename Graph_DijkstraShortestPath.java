/*
What is Dijkstra?
• Algorithm to find shortest path from one source to all vertices
• Works on weighted graphs
• Requires all edge weights ≥ 0
• Greedy approach using a min-heap
• Core idea: always expand the node with smallest current distance

---

How It Works (High Level)

• Initialize all distances = infinity
• Set source distance = 0
• Use min-heap to pick smallest distance node
• Relax its neighbors (update if shorter path found)
• Repeat until heap is empty

---

Types of Dijkstra

1. Eager (Classic / Relaxation-Based)
   • Maintain dist[] with tentative distances
   • If shorter path found → update dist[]
   • Push updated value into heap
   • Use visited[] to finalize nodes

2. Lazy
   • Do NOT update dist repeatedly
   • Allow duplicate entries in heap
   • First time a node is popped → finalize
   • Skip stale entries afterward

---

Eager vs Lazy Comparison

Eager:
• Uses relaxation condition (newDist < dist[nbr])
• Conceptually cleaner
• Closer to textbook
• Slightly more controlled heap usage

Lazy:
• Simpler implementation
• No explicit relaxation check for finalized nodes
• More duplicate entries in heap
• Very practical in Java (since no decrease-key support)

Both:
• Time → O((V + E) log V)
• Space → O(V + E)
• Require non-negative weights

---

When to Use Dijkstra

• Road navigation (Google Maps)
• Network routing (OSPF)
• Cheapest flight problems
• Game pathfinding
• Latency optimization systems

---

When NOT to Use

• Graph has negative edges → use Bellman-Ford
• Need shortest path between all pairs → use Floyd-Warshall or repeated Dijkstra
*/
import java.util.*;

class Solution {

    static int[] dijkstra(int V, ArrayList<ArrayList<int[]>> adj, int src) {

        // dist[i] = shortest known distance from src to i
        int[] dist = new int[V];

        // visited[i] = whether shortest distance finalized
        boolean[] visited = new boolean[V];

        // Initially set all distances to infinity
        Arrays.fill(dist, Integer.MAX_VALUE);

        // Distance to source is 0
        dist[src] = 0;

        // Min-heap: stores {node, distance}
        // Lambda comparator → smaller distance first
        PriorityQueue<int[]> pq =
                new PriorityQueue<>((a, b) -> a[1] - b[1]);

        // Push source
        pq.add(new int[]{src, 0});

        while (!pq.isEmpty()) {

            int[] curr = pq.poll();
            int node = curr[0];

            // Skip if already processed
            if (visited[node])
                continue;

            // Mark as finalized
            visited[node] = true;

            // Explore neighbors
            for (int[] edge : adj.get(node)) {

                int neighbor = edge[0];
                int weight = edge[1];

                // Relaxation:
                // If new shorter path found
                if (!visited[neighbor] &&
                    dist[node] + weight < dist[neighbor]) {

                    dist[neighbor] = dist[node] + weight;

                    // Push updated distance
                    pq.add(new int[]{neighbor, dist[neighbor]});
                }
            }
        }

        return dist;
    }
}






import java.util.*;

class Solution {

    static int[] dijkstra(int V, ArrayList<ArrayList<int[]>> adj, int src) {

        // shortest[i] = finalized shortest distance
        int[] shortest = new int[V];

        Arrays.fill(shortest, Integer.MAX_VALUE);

        // Min-heap based on distance
        PriorityQueue<int[]> pq =
                new PriorityQueue<>((a, b) -> a[1] - b[1]);

        // Push source with distance 0
        pq.add(new int[]{src, 0});

        while (!pq.isEmpty()) {

            int[] curr = pq.poll();
            int node = curr[0];
            int distance = curr[1];

            // If already finalized, skip
            if (shortest[node] != Integer.MAX_VALUE)
                continue;

            // First time popped → shortest confirmed
            shortest[node] = distance;

            // Push neighbors
            for (int[] edge : adj.get(node)) {

                int neighbor = edge[0];
                int weight = edge[1];

                if (shortest[neighbor] == Integer.MAX_VALUE) {

                    pq.add(new int[]{neighbor,
                                     distance + weight});
                }
            }
        }

        return shortest;
    }
}






//{ Driver Code Starts
import java.util.*;
import java.io.*;
import java.lang.*;

class Solution
{
    //Function to find the shortest distance of all the vertices  //from the source vertex S.
    public static class Pair implements Comparable<Pair>{
        int v; int wt;
        public Pair(int v, int wt){ this.v = v; this.wt = wt; }
        public int compareTo(Pair pair){ return this.wt-pair.wt;  }
    }
    static int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S){
        int[] ans=new int[V];
        Arrays.fill(ans, Integer.MAX_VALUE);
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(S, 0));
        while(pq.size()>0){
            Pair rem = pq.remove();
            if(ans[rem.v]!=Integer.MAX_VALUE)
                continue;
            ans[rem.v] = rem.wt;
            ArrayList<ArrayList<Integer>> nbrsList = adj.get(rem.v);
            for(ArrayList<Integer> nbr : nbrsList){
                int nbrName = nbr.get(0);
                if(ans[nbrName]!=Integer.MAX_VALUE)
                    continue;
                int nbrWt = nbr.get(1);
                pq.add(new Pair(nbrName, rem.wt + nbrWt));
            }
        }
        return ans;
    }
}
class DriverClass
{
    public static void main(String args[]) throws IOException {

        BufferedReader read =
            new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            String str[] = read.readLine().trim().split(" ");
            int V = Integer.parseInt(str[0]);
            int E = Integer.parseInt(str[1]);
    
            ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<ArrayList<ArrayList<Integer>>>();
            for(int i=0;i<V;i++)
            {
                adj.add(new ArrayList<ArrayList<Integer>>());
            }
            
            int i=0;
            while (i++<E) {
                String S[] = read.readLine().trim().split(" ");
                int u = Integer.parseInt(S[0]);
                int v = Integer.parseInt(S[1]);
                int w = Integer.parseInt(S[2]);
                ArrayList<Integer> t1 = new ArrayList<Integer>();
                ArrayList<Integer> t2 = new ArrayList<Integer>();
                t1.add(v);
                t1.add(w);
                t2.add(u);
                t2.add(w);
                adj.get(u).add(t1);
                adj.get(v).add(t2);
            }
            
            int S = Integer.parseInt(read.readLine());
            
            Solution ob = new Solution();
            
            int[] ptr = ob.dijkstra(V, adj, S);
            
            for(i=0; i<V; i++)
                System.out.print(ptr[i] + " ");
            System.out.println();
        }
    }
}
/*
https://practice.geeksforgeeks.org/problems/implementing-dijkstra-set-1-adjacency-matrix/1
Given a weighted, undirected and connected graph of V vertices and an adjacency list adj where adj[i] is a list of lists containing two integers where the first integer of each list j denotes there is edge between i and j , second integers corresponds to the weight of that  edge . You are given the source vertex S and You to Find the shortest distance of all the vertex's from the source vertex S. You have to return a list of integers denoting shortest distance between each node and Source vertex S.
 

Note: The Graph doesn't contain any negative weight cycle.

 

Example 1:

Input:
V = 2
adj [] = {{{1, 9}}, {{0, 9}}}
S = 0
Output:
0 9
Explanation:

The source vertex is 0. Hence, the shortest 
distance of node 0 is 0 and the shortest 
distance from node 1 is 9.
 

Example 2:

Input:
V = 3, E = 3
adj = {{{1, 1}, {2, 6}}, {{2, 3}, {0, 1}}, {{1, 3}, {0, 6}}}
S = 2
Output:
4 3 0
Explanation:

For nodes 2 to 0, we can follow the path-
2-1-0. This has a distance of 1+3 = 4,
whereas the path 2-0 has a distance of 6. So,
the Shortest path from 2 to 0 is 4.
The shortest distance from 0 to 1 is 1 .
 

Your Task:
You don't need to read input or print anything. Your task is to complete the function dijkstra()  which takes the number of vertices V and an adjacency list adj as input parameters and Source vertex S returns a list of integers, where ith integer denotes the shortest distance of the ith node from the Source node. Here adj[i] contains a list of lists containing two integers where the first integer j denotes that there is an edge between i and j and the second integer w denotes that the weight between edge i and j is w.

 

Expected Time Complexity: O(V2).
Expected Auxiliary Space: O(V2).

*/

