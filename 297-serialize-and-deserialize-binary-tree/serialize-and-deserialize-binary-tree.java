/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    private StringBuilder sb;   // single shared buffer
    public String serialize(TreeNode root) {
        sb = new StringBuilder();
        dfs(root);
        return sb.toString();
    }
    void dfs(TreeNode root) {
        if (root == null) {
            sb.append("null,");
            return;
        }
        sb.append(root.val).append(",");
        dfs(root.left); dfs(root.right);
    }
    // Decodes your encoded data to tree.
    private String[] data;
    private int idx;
    public TreeNode deserialize(String s) {
        data = s.split(",");
        idx = 0;
        return dfs();
    }

    TreeNode dfs() {
        if (data[idx].equals("null")) {
            idx++;
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(data[idx++]));
        node.left = dfs();
        node.right = dfs();
        return node;
    }

}
/*
Approach  using tree -> array based on indices
root = 1
L = 2*i + 1
R = 2i+2
[1,2,3,n,n,4,5] => Array-> String
*/

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));