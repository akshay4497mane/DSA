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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        if(root==null) return root;
        List<TreeNode> A = Arrays.asList(nodes);
        if (A.contains(root)) { return root; }
        TreeNode leftAns = lowestCommonAncestor(root.left, nodes);
        TreeNode rightAns = lowestCommonAncestor(root.right, nodes);
        if( leftAns!=null && rightAns!=null)
            return root;
        return (leftAns == null)? rightAns : leftAns;
    }
}