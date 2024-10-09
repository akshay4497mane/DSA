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
/* Recursive
Checks if two binary trees are identical using recursion:
1. If both nodes are null, trees are identical.
2. If one node is null and the other is not, trees are not identical.
3. Recursively check if current node values are equal, and left and right subtrees are identical.
TIME : O(N), SPACE : O(height) = O(N)
*/
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if( p == null && q == null) return true;
        if( p==null || q == null ) return false;
        return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}