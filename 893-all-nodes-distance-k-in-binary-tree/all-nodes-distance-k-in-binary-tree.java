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
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        buildParent(root, parent);
        //for( var entry : parent.entrySet())
        //    System.out.println(entry.getKey().val +" " + entry.getValue().val);
        Queue<TreeNode> q = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        q.offer(target);
        visited.add(target);
        int level=0;
        while( !q.isEmpty() ){
            System.out.println("Level :" + level);
            q.stream().map(node -> node.val).forEach(val -> System.out.print(val + " "));
            System.out.println("");

            int levelSize = q.size();
            if(level == k){
                for(int i=0; i<levelSize; i++)
                //DONT USE for(int i=0; i<q.size(); i++) ||q.size() is gettng modified in loop
                    ans.add(q.poll().val);
                return ans;
            }
            for( int i=0; i<levelSize; i++ ){
                TreeNode curr = q.poll();
                if( curr.left != null && !visited.contains(curr.left) ){
                    q.offer(curr.left);
                    visited.add(curr.left);
                }
                if( curr.right != null && !visited.contains(curr.right) ){
                    q.offer(curr.right);
                    visited.add(curr.right);
                }
                TreeNode currParent = parent.get(curr);
                if( currParent != null && !visited.contains(currParent)){
                    q.offer( currParent);
                    visited.add(currParent);
                }
            }
            level++;
        }
        return ans;               
    }
    void buildParent(TreeNode root, Map<TreeNode, TreeNode> parent){
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        //parent.put( root, null);
        while( !q.isEmpty() ){
            TreeNode curr = q.poll();
            if( curr.left != null ){
                parent.put(curr.left, curr);
                q.offer(curr.left);
            }
            if( curr.right != null ){
                parent.put(curr.right, curr);
                q.offer(curr.right);
            }            
        }
    }
}