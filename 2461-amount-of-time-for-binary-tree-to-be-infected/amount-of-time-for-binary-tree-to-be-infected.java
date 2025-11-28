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
    /*
    Approach
    1. DFS( create parents map | find start node )
    2. BFS(start from START, explore childs,parents not yet visited) -> time++ for every level
    3. maintain visited map
        parents(node -> p)[ 3->1, 5->1, 4->5, 10->3, 6->3, 9->4, 2->4]
        BFS -> time++
        -> explore all child's + parents
        //10,0 | 3,1 | 6,2 1,2 | 5,3 | 4,4 | 9,5 2,5
        visited[node, boolean]
    */
    public int amountOfTime(TreeNode root, int start) {
        findParents(root, start);
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(startNode);
        int time=0;
        Set<TreeNode> burnVisited = new HashSet<>();
        while(!q.isEmpty()){ //Q = 3 / 6 10 1 / 
            int levelSize = q.size();
            for(int i=0; i<levelSize; i++){
                TreeNode curr = q.remove();
                burnVisited.add(curr);
                if(curr.left!=null && !burnVisited.contains(curr.left)) q.add(curr.left);
                if(curr.right!=null && !burnVisited.contains(curr.right)) q.add(curr.right);
                TreeNode p = parentMap.get(curr);
                if(p!=null && !burnVisited.contains(p)) q.add(p);                
            }
            time++;
        }        
        return time-1;
    }
    Map<TreeNode, TreeNode> parentMap = new HashMap<>();
    TreeNode startNode = null;
    void findParents(TreeNode root, int start){
        if(root==null) return;
        if(root.val == start) startNode = root;
        if(root.left != null){
            parentMap.put(root.left, root);
            findParents(root.left, start);
        } 
        
        if(root.right != null){
            parentMap.put(root.right, root);
            findParents(root.right, start);
        } 

    }
}