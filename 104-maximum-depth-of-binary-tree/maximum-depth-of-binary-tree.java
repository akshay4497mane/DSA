/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    /*
    Max Depth = No of Nodes in longest path from root to leaf
     * RECURSIVE DFS
     * Time: O(N) visit each nodes once,
     * SPACE : size of recursive stack = O(height) = best O(logN)/worst O(NN)
     */
    public int maxDepth_Recursive(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    /* Iterative DFS using Stack 
    - Use Stack<TreeNode> for recursion
    - Use Stack<Integer> to keep track of current depth
    Time: O(N) / Space O(log N)
    */
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        LinkedList<TreeNode> stackNodes = new LinkedList<>();
        LinkedList<Integer> stackDepths = new LinkedList<>();
        stackNodes.add(root); stackDepths.add(1);
        int ansMaxDepth = 0, currDepth = 0;
        TreeNode currNode = null;
        while (!stackNodes.isEmpty()) {
            currNode = stackNodes.pollLast();
            currDepth = stackDepths.pollLast();
            if (currNode != null) {
                ansMaxDepth = Math.max(ansMaxDepth, currDepth);
                stackNodes.add(currNode.left);
                stackDepths.add(currDepth + 1);
                stackNodes.add(currNode.right);
                stackDepths.add(currDepth + 1);
            }
        }
        return ansMaxDepth;
    }

}