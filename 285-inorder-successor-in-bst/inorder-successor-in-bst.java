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
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode ans = null;
        while( root != null && root.val != p.val ){
            if( p.val < root.val ){
                ans = root;
                root = root.left;
            }else if( p.val > root.val ){

                root = root.right;
            }
        }

        if (root == null) return null;
        if (root.right != null) {
            TreeNode cur = root.right;
            while (cur.left != null) cur = cur.left;
            return cur;
        }

        return ans;
    }
}