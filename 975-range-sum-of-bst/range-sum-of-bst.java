class Solution {
    int ans;
    
    public int rangeSumBST(TreeNode root, int low, int high) {
        ans = 0;
        dfs(root, low, high);
        return ans;
    }
    /* DFS approach : 
    Time Complexity: O(N) in worst case (skewed tree), O(log N) in balanced BST
    Space Complexity: O(H) for recursion stack (H = height of BST)
    Optimized: Avoids unnecessary traversal when value is out of range 
    */
    public void dfs(TreeNode root, int low, int high) {
        if (root != null) {
            if (low <= root.val && root.val <= high) 
                ans += root.val; // Add value if in range
            
            if (low < root.val) 
                dfs(root.left, low, high); // Search left if needed
            
            if (high > root.val) 
                dfs(root.right, low, high); // Search right if needed
        }
    }
}


