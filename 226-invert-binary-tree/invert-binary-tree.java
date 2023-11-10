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
}