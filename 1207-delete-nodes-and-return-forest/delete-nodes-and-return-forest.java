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