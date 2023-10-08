/*

Problem Description
 
 

You are given the root of a binary tree A.
You have to find out the number of parent - child relationship whose values are consecutive numbers.



Problem Constraints
1 <= Number of nodes in the tree <= 105


Input Format
The first argument is the root of the binary tree A.


Output Format
Return a single integer denoting the number of consecutive parent - child relationships.


*/


/**
 * Definition for binary tree
 * class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) {
 *      val = x;
 *      left=null;
 *      right=null;
 *     }
 * }
 */
public class Solution {
    
    /* RECURSION : Time: O(N) , Space: O(N) 
    public int consecutiveNodes(TreeNode A) {
        int leftAns =0, rightAns = 0;
        if(A == null)
            return 0;
        if(A.left != null)
            if( Math.abs( A.left.val - A.val ) == 1 )
                leftAns = 1 + consecutiveNodes(A.left);
            else
                leftAns = 0 + consecutiveNodes(A.left);                

        if(A.right != null)
            if( Math.abs( A.right.val - A.val) == 1 )
                rightAns = 1 + consecutiveNodes(A.right);
            else
                rightAns = 0 + consecutiveNodes(A.right);

        return leftAns + rightAns;
    }    
    */

    /* LEVEL Order Traversal : Using Queue
    
    */
    public int consecutiveNodes(TreeNode A) {
        if(A == null) return 0;
        int count = 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(A);
        while(!queue.isEmpty()){
            int size = queue.size();
            TreeNode curr = queue.poll();
            int val = curr.val;
            if(curr.left != null){
                queue.add(curr.left);
                int diff = Math.abs(val - curr.left.val);
                if(diff == 1) count++;
            }
            if(curr.right != null){
                queue.add(curr.right);
                int diff = Math.abs(val - curr.right.val);
                if(diff == 1) count++;
            }
        }
        return count;
    }
