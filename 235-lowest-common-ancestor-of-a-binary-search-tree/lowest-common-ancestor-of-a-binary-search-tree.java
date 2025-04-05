/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null || root==p || root==q) return root;
        
        if( p.val < root.val )
            if( q.val < root.val )
                return lowestCommonAncestor(root.left, p, q);
            else
                return root;
        else
            if( q.val < root.val )
                return root;
            else
                return lowestCommonAncestor(root.right, p, q);
    }
}