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
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        //Find deepest leaves | first, last node in deepest level
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        TreeNode curr, deepestLeft = null, deepestRight=null;
        while(!q.isEmpty()){
            int levelSize = q.size();
            System.out.println("Level : ");
            for(int i=0; i<levelSize; i++){
                curr = q.remove();
                if(i==0)deepestLeft = curr;
                if(i==levelSize-1)deepestRight = curr;                
                System.out.print(curr.val);
                if(curr.left!=null) q.add(curr.left);
                if(curr.right!=null) q.add(curr.right);
            }
        }
        //FIND LCA
        System.out.println("Deep: " + deepestLeft.val + " " + deepestRight.val);
        if(deepestLeft == deepestRight) return deepestLeft;
        return LCA(root, deepestLeft, deepestRight);
    }
    TreeNode LCA(TreeNode root, TreeNode p, TreeNode q){
        if(root==null || root==p || root==q) return root;
        TreeNode leftAns = LCA(root.left, p, q);
        TreeNode rightAns = LCA(root.right, p, q);
        if(leftAns!=null && rightAns != null) return root;
        return (leftAns != null)? leftAns : rightAns;
    }
}