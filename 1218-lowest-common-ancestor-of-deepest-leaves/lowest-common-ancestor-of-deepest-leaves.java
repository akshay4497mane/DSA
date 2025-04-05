/*
Problem: Find the lowest common ancestor (LCA) of the deepest leaves in a binary tree.

Sample Input: [1,2,3,4,5,null,6,null,null,7]
Sample Output: Node with value 2
*/

class Solution {
/*
Approach 2: ( DFS )
Using 1-pass DFS.
For each subtree, return a pair of (depth, LCA node). Recurse and compare depths.
Time: O(N) - visit each node once
Space: O(H) - recursion stack space, H = height of tree

*/
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return dfs(root).LCANode; // Return LCA node from result pair
    }
    private PairLCA dfs(TreeNode root) {
        if (root == null) return new PairLCA(root, 0); // Base case: empty node has depth 0, RCA null

        PairLCA leftAns = dfs(root.left); // Recurse into left subtree
        PairLCA rightAns = dfs(root.right); // Recurse into right subtree

        if (leftAns.depth > rightAns.depth) // Left subtree is deeper
            return new PairLCA(leftAns.LCANode, leftAns.depth + 1); // Propagate deeper LCA up with updated depth

        if (leftAns.depth < rightAns.depth) // Right subtree is deeper
            return new PairLCA(rightAns.LCANode, rightAns.depth + 1); // Propagate deeper LCA up with updated depth

        return new PairLCA(root, leftAns.depth + 1); // Equal depth → current root node is LCA
    }
    private class PairLCA {
        /*Alternative approach : Pair<TreeNode, Integer> pair = new Pair<>(node, 0); 
        pair.getKey() returns TreeNode | pair.getValue() returns int depth |
        Cons: Less readabl
        */
        TreeNode LCANode; // LCA node at this subtree
        int depth; // Max depth in this subtree
        PairLCA(TreeNode lca, int dep) {
            this.LCANode = lca; this.depth = dep;
        }
    }

/*
Approach 1 : ( BFS )
- Use level-order traversal to get the leftmost and rightmost nodes at the deepest level.
- Then find the LCA of those two nodes.
Time Complexity: O(N)
Space Complexity: O(H)
*/
    public TreeNode lcaDeepestLeaves_Approach1(TreeNode root) {
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
