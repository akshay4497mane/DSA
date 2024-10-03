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
    public TreeNode invertTree_Recursive(TreeNode root) {
        if( root == null ) return root;
        TreeNode newRight = invertTree(root.left);
        TreeNode newLeft = invertTree(root.right);
        root.left = newLeft;
        root.right = newRight;
        return root;
    }

    public TreeNode invertTree(TreeNode root) {
        if( root == null ) return null;
        Queue<TreeNode> q = new LinkedList<>();
        q.add( root );
        while( !q.isEmpty() ){
            TreeNode curr = q.poll();
            TreeNode temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;
            if(curr.left != null) q.add( curr.left );
            if(curr.right != null) q.add( curr.right );
        }
        return root;
    }
}