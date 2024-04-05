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
/*
APPROACH : 
Calculate Height recursively, calculate rows, columns needed in output matrix
initialize empty ans List
print recursively root element at mid of every level
*/
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> ans = new ArrayList();
        int height = getHeight(root), row = height + 1, col = (int)Math.pow(2, height+1) - 1;
        for(int r = 0 ; r < row ; r++){
            List<String> list = new ArrayList();
            for(int c = 0; c< col ; c++){
                list.add("");
            }
            ans.add(list);
        }
        int left = 0, right = col-1, level=0;
        print(left, right, level, root, ans);
        return ans;
    }
    void print(int left, int right, int level, TreeNode root, List<List<String>> ans){
        if(root==null) return;
        int mid = left + (right -left)/2;
        ans.get(level).set(mid, String.valueOf(root.val));
        print(left, mid-1, level+1, root.left, ans);
        print(mid+1, right, level+1, root.right, ans);
    }
    int getHeight(TreeNode root){
        if(root == null) return -1;
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }
}