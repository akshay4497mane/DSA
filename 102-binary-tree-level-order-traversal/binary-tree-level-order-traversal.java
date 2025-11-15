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
    approach 1 : While Loop till q isEmpty | Calculate size of queue as currLevelSize=> run FOR Loop | process children if Not Null
    approach 2 : add Marker
    approach 3 : Using 2 Queues
*/
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        while(!q.isEmpty()){
            int currLevelSize = q.size();
            List<Integer> currLevelQ = new ArrayList<>();
            for(int i=0; i<currLevelSize; i++){
                TreeNode curr = q.remove();
                currLevelQ.add(curr.val);
                if(curr.left!=null) q.add(curr.left);
                if(curr.right!=null) q.add(curr.right);
            }
            ans.add(currLevelQ);
        }
        return ans;
    }
}