/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    //Approach1: Recursive using Call Stack
    public TreeNode invertTreeRecursive(TreeNode root) {
        if(root == null) return root;
        TreeNode temp = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = temp;
        return root;
    }
    //Approach2: Iterative using Dynamic Heap Memory Stack
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return root;
        Stack<TreeNode> st = new Stack<>();
        //Use Dequeue<TreeNode> st = new ArrayDequeue<>();
        //Use Dequeue<TreeNode> st = new LinkedList<>();
        
        st.push(root);
        while( !st.isEmpty() ){
            TreeNode node = st.pop();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if( node.left != null ) st.push(node.left);
            if( node.right != null ) st.push(node.right);
        }
        return root;
    }
    //Approach 3 : Use QUEUE  instead of stack | Level order to achieve same

    /*
    Recursion vs Stack Iteration 
    RAM Memory vs HDD memory
    Slow context switching vs Fast
    https://stackoverflow.com/questions/35097729/recursion-vs-stack?rq=3
    */

    /* 
    Java Stack which to use?  
    Deque vs Stack
    Interface vs Class
    Not thread safe vs Thread safe
    supports insert/delet both ends vs NA
    NA vs st.get(1), st.put(1) modification risk as it extends vector
    Iteration Top to bottom vs bottom to top

    https://www.baeldung.com/java-deque-vs-stack

    https://stackoverflow.com/questions/12524826/why-should-i-use-deque-over-stack

    */
}