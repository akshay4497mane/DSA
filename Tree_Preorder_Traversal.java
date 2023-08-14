/*
Interviewbits Problem:  
https://www.interviewbit.com/problems/preorder-traversal/

*/
/**
 * Definition for binary tree
 * class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) {
 *      val = x;
 *      left=null;
 *      right=null;
 *     }
 * }
 */
public class Solution {
    //Approach 1 : Using Recursion| Time: O(N), Space: O(height) < varies from O( log(N) ) to O(N) >
    /*
    public ArrayList<Integer> preorderTraversal(TreeNode A) {
        ArrayList<Integer> ans = new ArrayList<>();
        if(A == null)
            return ans;
        ans.add(A.val);
        ans.addAll( preorderTraversal(A.left) );
        ans.addAll( preorderTraversal(A.right) );
        return ans;
    }
    */
    
    //Approach 2 : Using STACK | Time: O(N), Space: O(height) < varies from O( log(N) ) to O(N) >
    /*
    public ArrayList<Integer> preorderTraversal(TreeNode A) {
        ArrayList<Integer> ans = new ArrayList<>();

        Stack<TreeNode> st = new Stack<>();            
        if(A != null)
            st.push(A);
        while(! st.empty()){
            TreeNode curr = st.pop();
            ans.add(curr.val);
            if(curr.right != null)
                st.push(curr.right);
            if(curr.left != null)
                st.push(curr.left);
        }
        return ans;
    }
    */
    
    //Approach 3 : MORRIS Preorder(X Recursion, X Stack) | Time: O(N), Space: O(1)
    public ArrayList<Integer> preorderTraversal(TreeNode Head) {
        ArrayList<Integer> ans = new ArrayList<>();
        
        TreeNode curr = Head;
        while( curr != null ) {
            
            if ( curr.left == null ) {
                ans.add(curr.val);
                curr = curr.right;
            } else {
                //Find In order predecessor
                TreeNode inPredecessor = curr.left;
                while( inPredecessor.right != null && inPredecessor.right != curr) {
                    inPredecessor = inPredecessor.right;
                }
                //Temporarily set RIGHT of IN predecessor as CURR
                if(inPredecessor.right == null){
                    inPredecessor.right = curr;
                    ans.add( curr.val );
                    curr = curr.left;
                }else if(inPredecessor.right == curr){
                    //REMOVE Temporary LINK between inPredecessor & CURR

                    inPredecessor.right = null;
                    curr = curr.right;
                }    
            }
        }
        return ans;        
    }    

    
}
