class Solution {
    Map<Node, Node> map = new HashMap<>(); //oldNode -> newNode
    public Node cloneGraph(Node node) {
        return dfs(node);
    }
    Node dfs(Node node){        
        if(node==null) return null;
        if(map.containsKey(node)) 
            return map.get(node);
        Node copyNode = new Node(node.val);
        map.put(node, copyNode);
        for( Node neigh : node.neighbors ){
            copyNode.neighbors.add( cloneGraph(neigh) );
        }
        return copyNode;
    }
}
/*
1 - 2
|   |
3 - 4
*/









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
//Use HashMap to store copied 
class Solution_1 {
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


    Map<Node, Node> map = new HashMap<>();
    public Node cloneGraph_DFS(Node node) {
        return dfs(node);
    }
    Node dfs(Node node){
        if(node==null)return node;
        if(map.containsKey(node)) 
            return map.get(node);
        Node copy = new Node(node.val);
        map.put(node, copy);
        for(Node neigh : node.neighbors){
            copy.neighbors.add(dfs(neigh));
        }
        return copy;
    }   
}