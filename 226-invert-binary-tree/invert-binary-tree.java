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
    /* Recursion : Time: O(N), Space O(height) */
    public TreeNode invertTree(TreeNode root) {
        if( root == null ) return root;
        TreeNode newRight = invertTree(root.left);
        TreeNode newLeft = invertTree(root.right);
        root.left = newLeft;
        root.right = newRight;
        return root;
    }
}