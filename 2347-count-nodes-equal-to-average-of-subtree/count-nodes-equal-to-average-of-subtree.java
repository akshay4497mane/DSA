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
class Pair{
    Integer sum;
    Integer count;
    Pair(Integer a, Integer b){
        this.sum = a; this.count = b;
    }
}
class Solution {
    int ansCount = 0;
    public int averageOfSubtree(TreeNode root) {
        DFS_traverse(root);
        return ansCount;
    }
    //Returns <Sum, Count>
    Pair DFS_traverse(TreeNode root){
        if(root == null) return new Pair(0, 0);
        Pair leftAns = DFS_traverse(root.left);
        Pair rightAns = DFS_traverse(root.right);
        int rootSum = leftAns.sum + rightAns.sum + root.val;
        int rootCount = leftAns.count + rightAns.count + 1;
        if( (rootSum / rootCount) == root.val )
            ansCount++;
        return new Pair(rootSum, rootCount);
    } 
}