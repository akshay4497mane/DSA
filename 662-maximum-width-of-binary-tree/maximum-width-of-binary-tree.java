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
    class Pair{
        TreeNode node;
        Integer ind;
        Pair(TreeNode node, Integer ind){
            this.node = node;
            this.ind = ind;
        }
    }
    public int widthOfBinaryTree(TreeNode root) {
        int ans=0;
        if(root==null) return ans;
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(root, 0));
        while(!q.isEmpty()){
            int levelSize = q.size();
            int minIndexForLevel = 0, maxIndexForLevel=0;
            for(int i=0; i<levelSize; i++){
                Pair curr = q.remove();
                if(curr.node.left!=null)
                    q.add(new Pair(curr.node.left, 2*curr.ind+1));
                if(curr.node.right!=null)
                    q.add(new Pair(curr.node.right, 2*curr.ind+2));
                if(i==0) minIndexForLevel = curr.ind;
                if(i==levelSize-1) maxIndexForLevel = curr.ind;    
            }
            ans = Math.max(ans, maxIndexForLevel-minIndexForLevel+1);
        }
        return ans;
    }
}