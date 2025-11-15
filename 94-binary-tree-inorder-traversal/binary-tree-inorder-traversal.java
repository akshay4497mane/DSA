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
//Iterative
    public List<Integer> inorderTraversal(TreeNode root) {      
        List<Integer> ans = new ArrayList<>();
        if(root==null) return ans;
        Stack<TreeNode> st = new Stack<>();
        TreeNode curr = root; //Step1 : initialize curr to Root
        while(curr!= null || !st.isEmpty()){ // Step2 : While curr Not null, Stack NOT Empty
            while(curr!=null){ //step 3 : Go root > left > left untill null
                st.push(curr);
                curr = curr.left;
            }
            curr = st.pop();
            ans.add(curr.val); //step 4 : print ans/ add to list
            curr = curr.right; //step 5 go 1 step to right
        }
        return ans;
    }

//Recursive
    public List<Integer> inorderTraversal_recursion(TreeNode root) {      
        List<Integer> ans = new ArrayList<>();
        inorder(root, ans);
        return ans;
    }
    void inorder(TreeNode root, List<Integer> ans){ //Left Root Right
        if(root==null) return;
        inorder(root.left, ans);
        ans.add(root.val);
        inorder(root.right, ans);
    }
}
/*
Inorder : L R Rt
  1
 2 3
4 5
St:
curr: null
Ans : 4 2 5 1
*/