/*
 * Problem: Given a binary tree, return its zigzag level order traversal.
 *          Nodes at each level should be traversed in alternating left-to-right and right-to-left order.
 *
 * Approach: Use BFS with a queue. Maintain a flag to track traversal direction.
 *           Use a deque to store level nodes and insert elements at the correct position.
 *
 * Time Complexity: O(N) - Each node is visited once.
 * Space Complexity: O(N) - Queue stores nodes at each level.
 */

import java.util.*;

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans; // Return empty list if tree is empty

        Deque<TreeNode> q = new LinkedList<>(); // Queue for BFS traversal
        q.addLast(root);
        q.addLast(null); // Marker for level separation

        List<Integer> levelList = new LinkedList<>(); // Stores current level nodes
        boolean leftToRight = true; // Direction flag

        while (!q.isEmpty()) {
            TreeNode currNode = q.removeFirst(); // Get the front node

            if (currNode != null) {
                // Add node value in the correct order based on traversal direction
                if (leftToRight) 
                    levelList.addLast(currNode.val);
                else 
                    levelList.addFirst(currNode.val);

                // Add child nodes to queue for next level
                if (currNode.left != null) q.addLast(currNode.left);
                if (currNode.right != null) q.addLast(currNode.right);
            } else { // Level is completed
                ans.add(levelList); // Add current level list to result
                levelList = new LinkedList<>(); // Reset for the next level

                if (!q.isEmpty()) q.addLast(null); // Add null marker for next level
                leftToRight = !leftToRight; // Flip traversal direction
            }
        }
        return ans;
    }
}
