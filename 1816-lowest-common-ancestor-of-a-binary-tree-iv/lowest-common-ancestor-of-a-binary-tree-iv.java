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
/*
Approach 2: 
Use DFS postorder traversal. Store target nodes in a HashSet.
At each node, if both left and right subtrees return non-null (i.e. found targets), current node is LCA.
Time Complexity: O(N) - visit each node once
Space Complexity: O(H + K) - H for recursion stack, K for HashSet of target nodes
*/
    Set<TreeNode> targetSet = new HashSet<>(); // Store all target nodes for O(1) lookup
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        Collections.addAll(targetSet, nodes); // Add all nodes from array to set using internal loop
        // Equivalent to: for (TreeNode node : nodes) targetSet.add(node); // Loop through nodes and insert into set
        return LCA_dfs(root); // Start DFS to find LCA
    }
    TreeNode LCA_dfs(TreeNode root){
        if(root == null || targetSet.contains(root)) return root; // Return if null or current node is one of the targets

        TreeNode leftLCA = LCA_dfs(root.left); // Recurse into left subtree
        TreeNode rightLCA = LCA_dfs(root.right); // Recurse into right subtree

        if(leftLCA != null && rightLCA != null) return root; // If targets found in both subtrees, current node is LCA

        return leftLCA == null ? rightLCA : leftLCA; // Return non-null result from either subtree
    }

    /*
    Approach 1:  (8 min)
    Not optimal/ O(Nodes) Arrays.asList | O(Nodes) A.contains(root)
    Should use HASH SET
    */
    public TreeNode lowestCommonAncestor_approach1(TreeNode root, TreeNode[] nodes) {
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