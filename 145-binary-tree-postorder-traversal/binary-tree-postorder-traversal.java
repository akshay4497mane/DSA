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
//Iterative
/*
Post : Left Righ Root
PRE : Root Left Right | Iterative (print Root - push right - push left)

Modified PRE for Post
print Root - push left - push right  =>  Root - Rt - Left => Reverse -> Left Rt Root
*/
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if(root==null) return ans;
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        while(!st.isEmpty()){
            TreeNode curr = st.pop();
            ans.add(curr.val);
            if(curr.left!=null) st.push(curr.left);
            if(curr.right!=null) st.push(curr.right);
        }
        Collections.reverse(ans);
        return ans;
    }

//Recursive
    public List<Integer> postorderTraversal_recursive(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        post(root, ans);
        return ans;
    }
    void post(TreeNode root, List<Integer> ans){
        if(root==null) return;
        post(root.left, ans);
        post(root.right, ans);
        ans.add(root.val);
    }
}