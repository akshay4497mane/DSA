/*
Problem:
---------
We are given a binary tree where each node has:
  - a value (points)
  - reference to parent

Two players move simultaneously:
  - Player A starts from the root and moves downward (towards children).
  - Player B starts from a given leaf node and moves upward (towards parent).

Scoring Rules:
  - If a node is visited by A first, A collects its value.
  - If a node is visited by B first, A gets 0.
  - If both arrive at the same time, they split the value (A gets value/2).

Objective:
  Calculate the maximum points Player A can collect.

Input:
  Root of binary tree, Leaf node where B starts.

Output:
  Maximum points Player A can collect.

Approach:
---------
1. Find path from root (A) to B's leaf node.
2. Compute depth of each node on path.
3. Compare arrival times of A (from root) and B (from leaf).
   - If A arrives earlier => A gets full node value.
   - If B arrives earlier => A gets 0.
   - If both arrive same time => A gets half.
4. Sum up all contributions.

Time Complexity: O(h) where h = height of tree (path length root-to-leaf)
Space Complexity: O(h) recursion stack (for DFS path finding)
*/

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode parent;  // given in problem

    TreeNode(int val) {
        this.val = val;
    }
}

public class ABTreeGame {

    // Function to calculate maximum points for Player A
    public int maxPoints(TreeNode root, TreeNode leafB) {
        // Step 1: Get path from root to B's leaf
        java.util.List<TreeNode> path = new java.util.ArrayList<>();
        findPath(root, leafB, path);

        int totalPoints = 0;
        int n = path.size();

        // Step 2: Simultaneous movement analysis
        for (int i = 0; i < n; i++) {
            TreeNode node = path.get(i);
            int distA = i;            // distance from root
            int distB = n - 1 - i;    // distance from leaf

            if (distA < distB) {
                totalPoints += node.val;   // A reaches first
            } else if (distA == distB) {
                totalPoints += node.val / 2; // both reach same time
            } else {
                totalPoints += 0;          // B reaches first
            }
        }
        return totalPoints;
    }

    // Helper to find path from root to target node
    private boolean findPath(TreeNode root, TreeNode target, java.util.List<TreeNode> path) {
        if (root == null) return false;

        path.add(root);
        if (root == target) return true;

        if (findPath(root.left, target, path) || findPath(root.right, target, path)) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    // Driver Example
    public static void main(String[] args) {
        /*
               10
             /   \
           20     30
          /  \
        40    50
        */

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(20);
        root.right = new TreeNode(30);
        root.left.left = new TreeNode(40);
        root.left.right = new TreeNode(50);

        // setting parent pointers
        root.left.parent = root;
        root.right.parent = root;
        root.left.left.parent = root.left;
        root.left.right.parent = root.left;

        TreeNode leafB = root.left.left; // B starts at node 40

        ABTreeGame game = new ABTreeGame();
        int result = game.maxPoints(root, leafB);
        System.out.println("Max points A can collect: " + result);
    }
}
