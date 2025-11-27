class Solution {
    List<String> ans = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        helper(root, new StringBuilder());
        return ans;
    }

    public void helper(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        int len = sb.length();   // save state
        sb.append(root.val);
        if (root.left == null && root.right == null) {
            ans.add(sb.toString());
        } else {
            sb.append("->");
        }
        helper(root.left, sb);
        helper(root.right, sb);        
        sb.setLength(len);   // rollback to parent state
    }
}
