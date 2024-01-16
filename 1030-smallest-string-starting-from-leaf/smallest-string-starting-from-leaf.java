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
    public String smallestFromLeaf(TreeNode root) {
        return helper(root, "");
    }
    public String helper(TreeNode root, String s){
        if(root == null) return null;
        String currStr = String.valueOf((char)('a' + root.val) ).concat( s );
        if( root.left == null && root.right == null ){
            return currStr;
        }
        String ansLeft = helper(root.left, currStr);
        String ansRight = helper(root.right , currStr);

        if(ansLeft == null) return ansRight;
        if(ansRight == null) return ansLeft;
        
        if ( ansLeft.compareTo(ansRight)  < 0 ){
            return ansLeft;
        }else{
            return ansRight;
        }
    }
}