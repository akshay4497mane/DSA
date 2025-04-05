/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
/* Approach 2: 
Iterative Solution using Stack for recursion, 
For each node put (node -> parent) in HashMap,
For all ancestors of P, put all in HashSet,
Now traverse all ancestors of Q from bottom to up, search in P, first find will be ans
*/    
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        Stack<TreeNode> st = new Stack<>();
            st.push(root);
        Map<TreeNode, TreeNode> parents = new HashMap<>();
            parents.put(root, null);
        //while( !st.isEmpty() ){ //DFS traversal, creating parents Map
        while (!parents.containsKey(p) || !parents.containsKey(q)) {
            TreeNode curr = st.pop();
            if( curr.left != null ){
                parents.put(curr.left, curr);
                st.push(curr.left);
            }
            if( curr.right != null ){
                parents.put(curr.right, curr);
                st.push(curr.right);
            }            
        }
//For all Ancestors of P, put all in HashSet,
        Set<TreeNode> PAset = new HashSet<>();
        while( p != null ){
            PAset.add(p);
            p = parents.get(p);
        }
//traverse Q ancestors, untill found in PASet
        while( !PAset.contains(q) ){
            q = parents.get(q);
        }
        return q;
    }

/*
Approach 1 : Recursive(root, p, q) return boolean, find p-q in left, right and curr, return true, whenever  left + right + mid = 2 | 
Time : O(N) , Space : O(height) ~ O(N)
*/
    public TreeNode lowestCommonAncestor_approach1(TreeNode root, TreeNode p, TreeNode q) {
        helper(root, p, q);
        return this.ans;
    }
    TreeNode ans;
    private boolean helper(TreeNode root, TreeNode p, TreeNode q){
        if( root == null ) return false;
        int midAns = ( root == p || root == q ) ? 1 : 0;
        int leftAns = ( helper(root.left, p, q) )? 1 : 0;
        int rightAns = ( helper(root.right, p, q))? 1 : 0;
        if(midAns + leftAns + rightAns >= 2)
            this.ans = root;
        return ( midAns + leftAns + rightAns > 0 );        
    }

//Fastest Recursive Solution :
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
        if(root == null || root == p || root == q)  return root; //found Ans
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left != null && right != null)   return root;
        return left != null ? left : right;
    }
}