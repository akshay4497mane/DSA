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
    /*
    Approach 2
    Time : O(N)
    Space : O(1) | No queue is needed
    */
    public Node connect(Node root) {
        if(root==null) return root;
        Node leftmost = root;

        while( leftmost.left != null ){ //stop when next level is empty
        //For each level N, we connect nodes in level N+1
            Node head = leftmost;
            while(head!=null){ //iterate linked list of next pointer to traverse all nodes in current level
                head.left.next = head.right; //Connection 1 : left->right
                if(head.next!=null)
                    head.right.next = head.next.left;//Connection2 : right to next left
                head = head.next;//continue linked list
            }
            leftmost = leftmost.left;//continue to next level
        }
        return root;
    }
    /*Approach 1 Level order Traversal
    Time : O(N)
    Space : O(N) | max queue size will be N/2 leaves
     */
    public Node connect_1(Node root) {
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