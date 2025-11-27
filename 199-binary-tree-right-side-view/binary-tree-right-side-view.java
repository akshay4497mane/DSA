class Solution {
        public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return new ArrayList<Integer>();
        ArrayDeque<TreeNode> queue = new ArrayDeque() {
            {
                offer(root);
            }
        };
        List<Integer> rightside = new ArrayList();
        while (!queue.isEmpty()) {
            int levelLength = queue.size();
            for (int i = 0; i < levelLength; ++i) {
                TreeNode node = queue.poll();
                if (i == levelLength - 1) {// if it's the rightmost element
                    rightside.add(node.val);
                }
                if (node.left != null) 
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
        }
        return rightside;
    }

    public List<Integer> rightSideView_12(TreeNode root) {
        if (root == null) return new ArrayList<Integer>();
        
        ArrayDeque<TreeNode> nextLevel = new ArrayDeque() {{ offer(root); }};
        ArrayDeque<TreeNode> currLevel = new ArrayDeque();        
        List<Integer> rightside = new ArrayList();
        
        TreeNode node = null;
        while (!nextLevel.isEmpty()) {
            // prepare for the next level
            currLevel = nextLevel;
            nextLevel = new ArrayDeque<>();

            while (! currLevel.isEmpty()) {
                node = currLevel.poll();

                // add child nodes of the current level
                // in the queue for the next level
                if (node.left != null) 
                    nextLevel.offer(node.left);    
                if (node.right != null) 
                    nextLevel.offer(node.right);
            }
            
            // The current level is finished.
            // Its last element is the rightmost one.
            if (currLevel.isEmpty()) 
                rightside.add(node.val);    
        }
        return rightside;
    }
}

class Solution_Approach2 {
/*
APPROACH 2 : 
Step 1 : Level Order Traversal Using TWO Array LISTS
Step 2 : Last element of every level => add it in ANS
Time: O(N), Space: O(Diameter)

Space: O(Height) for DFS, O(Width/Diameter) for BFS
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

 