class Solution {
    /* 
    DFS approach (Iterative):
    - Uses a stack for traversal instead of recursion.
    - Checks for null before processing or while inserting into stack.
    - Avoids unnecessary traversal when node value is out of range.
    
    Time Complexity: O(N) in worst case (skewed tree), O(log N) in balanced BST.
    Space Complexity: O(H) for stack (H = height of BST).
    */
    public int rangeSumBST(TreeNode root, int low, int high) {
        int ans = 0;
        Stack<TreeNode> st = new Stack<>();
        st.push(root); //no need for null check
        
        while (!st.isEmpty()) {
            TreeNode node = st.pop();
            if (node != null) { // Null check while processing
                if (low <= node.val && node.val <= high) 
                    ans += node.val;
                
                if (low < node.val) //search left
                    st.push(node.left); //no need for null check
                
                if (high > node.val) //search right
                    st.push(node.right); //no need for null check
            }
        }
        return ans;
    }

    /* DFS Recursive approach : 
    Time Complexity: O(N) in worst case (skewed tree), O(log N) in balanced BST
    Space Complexity: O(H) for recursion stack (H = height of BST)
    Optimized: Avoids unnecessary traversal when value is out of range 
    */
    int ans;
    public int rangeSumBST2(TreeNode root, int low, int high) {
        ans = 0;
        dfs(root, low, high);
        return ans;
    }
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


