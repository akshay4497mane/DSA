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
class Solution {
    public Node cloneGraph(Node node) {
        if(node == null) return node;
        Map<Node, Node> hmap = new HashMap<>(); // Old -> New Mapping
        Queue<Node> q = new LinkedList<>();
        Node newNode = new Node(node.val, new ArrayList<>());
            hmap.put(node, newNode);
            q.add(node);
        while( q.size() > 0 ){
            Node rem = q.remove();
            Node newRem = hmap.get(rem);
            for( Node nbr : rem.neighbors ){
                if( hmap.containsKey(nbr) == false ){
                    Node newNbr = new Node(nbr.val, new ArrayList<>());
                    hmap.put( nbr, newNbr );
                    q.add(nbr);
                }
                Node newNbr = hmap.get(nbr);
                newRem.neighbors.add( newNbr );    
            }
        }
        return newNode;
    }
}