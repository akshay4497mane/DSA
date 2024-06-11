class Solution {
/*
APPROACH 2 : 
Step 1 : Level Order Traversal, 
Step 2 : Last element of every level => add it in ANS
*/
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ansRightSide = new ArrayList<>();
        List<TreeNode> currLevel = new ArrayList<>();
        if(root == null) return ansRightSide;
        currLevel.add(root);
        while( !currLevel.isEmpty() ){
            //currLevel.stream().forEach((x) -> System.out.print(x.val));
            int lastValue = currLevel.get(currLevel.size()-1).val;
            ansRightSide.add( lastValue );
            List<TreeNode> nextLevel = new ArrayList<>();
            for( TreeNode node : currLevel ){
                if(node.left != null) nextLevel.add(node.left);
                if(node.right != null) nextLevel.add(node.right);                
            }
            currLevel = nextLevel;
        }
        return ansRightSide;
    }
}
class Solution_1 {
/* APPROACH 1 : Reverse PRE-Order Solution
Reverse = Root, Right, Left
Maintain currLevel value in every recursive call, 
ANS.size() = N implies we found right view values for N levels.
So when we find currLevel == ansRightSide.size()  => insert it into ANS
Time: O(N), Space: O(H)
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