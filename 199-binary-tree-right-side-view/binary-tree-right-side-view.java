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
/* APPROACH 1 : Reverse PRE-Order Solution 
*/
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ansRightSide = new ArrayList<>();
        preOrderReverse(0, root, ansRightSide );
        return ansRightSide;
    }
    void preOrderReverse(int currLevel, TreeNode root, List<Integer> ansRightSide){
        if(root==null) return;
        if( currLevel == ansRightSide.size() ){
            ansRightSide.add(root.val);
        }
        preOrderReverse(currLevel+1, root.right, ansRightSide );
        preOrderReverse(currLevel+1, root.left , ansRightSide );
    }

}