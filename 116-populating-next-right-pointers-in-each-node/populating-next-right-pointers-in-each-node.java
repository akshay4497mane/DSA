/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if(root == null) return root; // /* Edge case: empty tree */

        Queue<Node> q = new LinkedList<Node>();
        q.add(root); // /* Start BFS with root node */

        while(q.size() > 0) {
            int currLevelSize = q.size(); // /* Number of nodes at current level */

            for(int i = 0; i < currLevelSize; i++) {
                Node node = q.poll(); // /* Fetch current node */

                if(i < currLevelSize - 1) node.next = q.peek(); 
                // /* Connect to next node in queue (same level) */

                if(node.left != null) q.add(node.left);  // /* Add left child to queue */
                if(node.right != null) q.add(node.right); // /* Add right child to queue */
            }
        }

        return root; // /* Tree with 'next' pointers connected */
    }
}