class Solution {
    //Approach 1 : Recursive function helper(node, currStr), 
    //step 1 : base : node == null return;   
    //Step 2: append curr "node char" at start of currStr
    //Step 3 : Base : if LEAF node return currSTR
   // Recursively call left, right | compare both strings, return lexigoraphically smaller
    public String smallestFromLeaf(TreeNode root) {
        return helper(root, "");
    }
    public String helper(TreeNode root, String s){
        if(root == null) return null;

        String currStr = String.valueOf((char)('a' + root.val) ).concat( s );
        if( root.left == null && root.right == null ) //if LEAF node
            return currStr;
        String ansLeft = helper(root.left, currStr);
        String ansRight = helper(root.right , currStr);

        if(ansLeft == null) return ansRight;
        if(ansRight == null) return ansLeft;
        
        return ( ansLeft.compareTo(ansRight)  < 0 ) ? ansLeft : ansRight;
    }
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