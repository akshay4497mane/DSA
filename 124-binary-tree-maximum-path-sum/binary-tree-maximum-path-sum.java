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
    int maxSum;
    public int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        gainFromSubtree(root);
        return maxSum;
    }
    int gainFromSubtree(TreeNode root){
        if(root==null) return 0;
        int gainLeft = Math.max(gainFromSubtree(root.left), 0);
        int gainRight = Math.max(gainFromSubtree(root.right), 0);
        maxSum = Math.max(maxSum, gainLeft + gainRight + root.val);
        return Math.max(gainLeft, gainRight) + root.val;
    }
}