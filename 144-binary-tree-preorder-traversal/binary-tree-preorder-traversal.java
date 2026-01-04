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
    //iterative
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        if(root==null) return ans;
        st.push(root);
        while(!st.isEmpty()){
            TreeNode curr = st.pop();
            ans.add(curr.val);
            if(curr.right!=null) st.push(curr.right);
            if(curr.left!=null) st.push(curr.left);
        }
        return ans;
    }

    List<Integer> ans = new ArrayList<>();
    public List<Integer> preorderTraversal_recursive(TreeNode root) {
        preorder(root);
        return ans;
    }
    void preorder(TreeNode root){
        if(root==null) return;
        ans.add(root.val);
        preorder(root.left);
        preorder(root.right);
    }
}