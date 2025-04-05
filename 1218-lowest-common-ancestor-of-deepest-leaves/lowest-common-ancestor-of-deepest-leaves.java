/*
Problem: Find the lowest common ancestor (LCA) of the deepest leaves in a binary tree.

Sample Input: [1,2,3,4,5,null,6,null,null,7]
Sample Output: Node with value 2

Approach: 
- Use level-order traversal to get the leftmost and rightmost nodes at the deepest level.
- Then find the LCA of those two nodes.

Time Complexity: O(N)
Space Complexity: O(H)
*/

class Solution {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        Queue<TreeNode> q = new ArrayDeque<>(); // Queue for BFS
        q.add(root);

        TreeNode curr, leftmostDeepLeaf = null, rightmostDeepLeaf = null;

        while (!q.isEmpty()) {
            int levelSize = q.size(); // Number of nodes at current level

            for (int i = 0; i < levelSize; i++) {
                curr = q.remove(); // Current node

                if (i == 0) leftmostDeepLeaf = curr; // First node at this level
                if (i == levelSize - 1) rightmostDeepLeaf = curr; // Last node at this level

                if (curr.left != null) q.add(curr.left); // Add left child to queue if exists
                if (curr.right != null) q.add(curr.right); // Add right child to queue if exists
            }
        }

        if (leftmostDeepLeaf == rightmostDeepLeaf) return leftmostDeepLeaf; // Only one deepest node

        return LCA(root, leftmostDeepLeaf, rightmostDeepLeaf); // Find LCA of both deepest nodes
    }

    TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root; // Base case

        TreeNode leftAns = LCA(root.left, p, q); // Recur on left subtree
        TreeNode rightAns = LCA(root.right, p, q); // Recur on right subtree

        if (leftAns != null && rightAns != null) return root; // p and q found in different subtrees

        return (leftAns != null) ? leftAns : rightAns; // Return non-null side
    }
}
