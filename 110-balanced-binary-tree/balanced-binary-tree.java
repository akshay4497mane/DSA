class Solution {
    public boolean isBalanced(TreeNode root) {
        return (-1 == depth(root)) ? false : true;
    }
    int depth(TreeNode root){ //return -1 if unbalanced | otherwise return depth
        if(root == null) return 0;
        int L = depth(root.left);
        int R = depth(root.right);
        if( L==-1 || R==-1 || Math.abs(L-R) > 1) 
            return -1;
        else        
            return 1 + Math.max(L,R);
    }
    /*
    public boolean isBalanced(TreeNode root) {
        if(root ==null) return true;
        int L = depth(root.left);
        int R = depth(root.right);
        System.out.println(root.val + " : " + L + "," + R);
       if ( Math.abs( L -  R) <=1  && isBalanced(root.left) && isBalanced(root.right) )
            return true;
        else
            return false;
    }
    int depth(TreeNode root){
        if(root ==null) return 0;
        return 1 + Math.max(depth(root.left), depth(root.right)); 
    }
    */
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