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
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> ans = new ArrayList<>();
        Set<Integer> deleteSet = new HashSet<>();
        for(int node : to_delete) deleteSet.add(node);
        if(!deleteSet.contains(root.val)) ans.add(root);
        dfs(root, ans, deleteSet);
        return ans;
    }
    TreeNode dfs( TreeNode root, List<TreeNode> ans, Set<Integer> deleteSet ){ //post order DFS
        if(root==null) return null;
        root.left = dfs(root.left, ans, deleteSet);
        root.right = dfs(root.right, ans, deleteSet);
        if(deleteSet.contains(root.val)){
            if(root.left != null) ans.add(root.left);
            if(root.right != null) ans.add(root.right);
            return null;
        }
        return root;
    }
}