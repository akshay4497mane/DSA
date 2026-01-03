//Use HashMap to store {oldNode -> newCopyNode }

class Solution {
    Map<Node, Node> map = new HashMap<>(); //oldNode -> newNode
    // DFS
    public Node cloneGraph_DFS(Node node) {
        return dfs(node);
    }
    Node dfs(Node node){        
        if(node==null) return null;
        if(map.containsKey(node)) 
            return map.get(node);
        Node newCopyNode = new Node(node.val);
        map.put(node, newCopyNode);
        for( Node neigh : node.neighbors ){
            newCopyNode.neighbors.add( cloneGraph(neigh) );
        }
        return newCopyNode;
    }

    // BFS
    public Node cloneGraph(Node node) {
        if(node==null) return node;
        Map<Node, Node> map = new HashMap<>();//oldNode -> New Node
        Queue<Node> q = new ArrayDeque<>();
        q.add(node);
        map.put(node, new Node(node.val));
        while(!q.isEmpty()){
            Node curr = q.remove();
            for(Node neigh : curr.neighbors){
                if(!map.containsKey(neigh)){
                    map.put(neigh, new Node(neigh.val));
                    q.add(neigh);
                }
                Node copyCurr = map.get(curr);
                Node copyNeigh = map.get(neigh);
                copyCurr.neighbors.add( copyNeigh );
            }
        }
        return map.get(node);
    }
}

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/