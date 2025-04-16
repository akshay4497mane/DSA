/*
Leetcode 1110: Delete Nodes and Return Forest
Problem: Given a binary tree and a list of nodes to delete, return the forest (list of roots) after deletion.
Sample:
Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]

Approach 1 (Used): Post-order DFS to prune deleted nodes. O(n) time, O(h + d) space
Approach 2: BFS + Root Tracking manually. Same time, harder to manage state.
*/

class Solution1 {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> ans = new ArrayList<>(); // Stores roots of resulting forest
        Set<Integer> deleteSet = new HashSet<>(); // Set for O(1) delete lookup
        for (int node : to_delete) deleteSet.add(node); // Add nodes to be deleted
        if (!deleteSet.contains(root.val)) ans.add(root); // If root is not in set deleted, add to result

        dfs(root, ans, deleteSet); // Start DFS to process tree
        return ans;
    }

    TreeNode dfs(TreeNode root, List<TreeNode> ans, Set<Integer> deleteSet) { // Post-order DFS
        if (root == null) return null; // Base case

        root.left = dfs(root.left, ans, deleteSet); // Process left subtree
        root.right = dfs(root.right, ans, deleteSet); // Process right subtree

        if (deleteSet.contains(root.val)) { // If current node needs to be deleted
            if (root.left != null) ans.add(root.left); // Add left child to result if not null
            if (root.right != null) ans.add(root.right); // Add right child to result if not null
            return null; // Return null to delete this node
        }

        return root; // Return current node if not deleted
    }
}


/*
Approach 2 : Use post-order DFS to delete nodes. 
Pass parent info to disconnect deleted nodes. 
This version uses VOID return type, deletes in place. 
O(n) time, O(h + d) space.
*/
class Solution2 {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> ans = new ArrayList<>(); // Stores roots of trees in forest
        Set<Integer> deleteSet = new HashSet<>(); // O(1) lookup for deletions
        for (int node : to_delete) deleteSet.add(node); // Fill delete set

        if (!deleteSet.contains(root.val)) ans.add(root); // Root added if not deleted

        dfs(root, null, false, deleteSet, ans); // Start DFS traversal

        return ans;
    }

    private void dfs(TreeNode node, TreeNode parent, boolean isLeft,
                     Set<Integer> deleteSet, List<TreeNode> ans) {
        if (node == null) return; // Base case

        dfs(node.left, node, true, deleteSet, ans); // Process left child
        dfs(node.right, node, false, deleteSet, ans); // Process right child

        if (deleteSet.contains(node.val)) { // If node needs to be deleted
            if (node.left != null) ans.add(node.left); // Add left child to forest if not null
            if (node.right != null) ans.add(node.right); // Add right child to forest if not null

            // Disconnect this node from its parent
            if (parent != null) {
                if (isLeft) parent.left = null; // Disconnect from left
                else parent.right = null; // Disconnect from right
            }
        }
    }
}

/*
Approach 3 : Passing IS_ROOT extra variable. Calling 
*/
class Solution {
    List<TreeNode> ans;
    Set<Integer> delete_set;
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        ans = new ArrayList<TreeNode>();
        delete_set = new HashSet<Integer>();
        for( Integer e : to_delete )
            delete_set.add(e);
        helper(root, true);
        return ans;
    }
    private TreeNode helper(TreeNode root, boolean isRoot){
        if(root == null) return null;
        boolean deleteFlag = delete_set.contains(root.val);
        if( isRoot && !deleteFlag ) ans.add(root);
        root.left = helper( root.left, deleteFlag );
        root.right = helper( root.right, deleteFlag );
        return deleteFlag ? null : root;
    }

}
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