/*
Find the Lowest Common Ancestor (LCA) of two nodes in a Binary Search Tree (BST).
Sample Input: BST = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
Sample Output: 6
*/

class Solution {
/* Approach 1 (recursive)
    Use BST property to traverse towards LCA in O(H) time, where H is tree height.
Time: O(H) - worst case height of tree  
Space: O(H) - recursion stack in worst case
*/
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root; // If root is null or matches p or q, return root

        if (p.val < root.val && q.val < root.val) // Both nodes are in left subtree
            return lowestCommonAncestor(root.left, p, q); // Recurse into left subtree

        if (p.val > root.val && q.val > root.val) // Both nodes are in right subtree
            return lowestCommonAncestor(root.right, p, q); // Recurse into right subtree

        return root; // One node on each side or root is one of the nodes → current node is LCA
    }
}
