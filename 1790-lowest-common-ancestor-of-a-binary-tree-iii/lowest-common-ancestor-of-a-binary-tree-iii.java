/* 
Problem: Given two nodes `p` and `q` of a binary tree with parent pointers, return their lowest common ancestor.
Sample Input: p = Node with val 5, q = Node with val 1
Sample Output: Node with val = 3 (Assuming 3 is their LCA)

Approach: Traverse from both nodes upwards using parent pointers; store ancestors of `p` in a set and return first common node found from `q`'s path.

Time Complexity: O(h) where h = height of the tree
Space Complexity: O(h) for storing ancestors of node `p`
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/

class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        if (p == null) return q; // If p is null, return q as the LCA candidate
        if (q == null) return p; // If q is null, return p as the LCA candidate

        Set<Node> PAncestors = new HashSet<>(); // To store all ancestors of p (including p itself)
        
        while (p != null) {
            PAncestors.add(p); // Add current node to set
            p = p.parent; // Move up towards root
        }

        while (q != null) {
            if (PAncestors.contains(q)) return q; // First node in q's path that is already in p's path is LCA
            q = q.parent; // Move up towards root
        }

        return null; // Should not reach here if tree is valid and both nodes are in the same tree
    }
}
