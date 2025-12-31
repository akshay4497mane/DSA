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
class Solution_1{
    int ans = 0;
    public int sumRootToLeaf(TreeNode root) {
        dfs(root, 0);   
        return ans; 
    }
    void dfs(TreeNode root, int currSum){
        if(root == null) return;
        int newSum = 10 * currSum + root.val;
        if(root.left==null && root.right==null)
            ans += convert(newSum);
        dfs(root.left, newSum);
        dfs(root.right, newSum);
    }
    int convert(int binaryNum){
        int ans = 0, pow=1;
        while( binaryNum != 0 ){
            int bit = binaryNum %10;
            ans += bit * pow;
            pow = pow * 2;
            binaryNum /= 10;     
        }
        return ans;
    }
}

class Solution_2 {
    int ans = 0;
    public int sumRootToLeaf(TreeNode root) {
        dfs(root, 0);
        return ans;
    }
    void dfs(TreeNode root, int currSum){
        if(root == null) return;
        int newSum = 2 * currSum + root.val;
                //curr = (curr << 1) | node.val;

        if(root.left == null && root.right==null) 
            ans +=newSum;
        dfs(root.left, newSum);
        dfs(root.right, newSum);
    }
}

class Solution {
    int ans = 0;

    public int sumRootToLeaf(TreeNode root) {
        dfs(root, 0);
        return ans;
    }

    void dfs(TreeNode node, int curr) {
        if (node == null) return;

        curr = (curr << 1) | node.val;

        if (node.left == null && node.right == null) {
            ans += curr;
            return;
        }

        dfs(node.left, curr);
        dfs(node.right, curr);
    }
}

