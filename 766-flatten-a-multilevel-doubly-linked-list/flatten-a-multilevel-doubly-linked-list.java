/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    /*
    Approach 1 : Recursive DFS
    intuition: similar to Preorder Traversal(Root, child, next)
    but since we need to modify pointers, we need PREV, CURR as inputs and we should return TAIL 
    flattenDFS(PREV, CURR) -> returns TAIL
        -connect prev,curr & curr to prev
        -store curr next temporarily
        -TAIL_ANS = flattenDFS(curr, curr.child)
        -Tail next = flattenDFS(tail, currNeXT)

    //Time: O(N), Space: O(N)
    */
    public Node flatten(Node head) {
        if(head==null) return head;
        Node dummyNode = new Node(0,null, head, null);//
        flattenDFS(dummyNode, head);
        dummyNode.next.prev = null;
        return dummyNode.next;
    }
    Node flattenDFS(Node prev, Node curr){
        if(curr==null) return prev;
        curr.prev = prev;
        prev.next = curr;
        Node currNext = curr.next;
        Node tail = flattenDFS(curr, curr.child);
        curr.child = null;
        return flattenDFS(tail, currNext);
    }

//APPROACH 2: Recursion but More elegant Solution
Node tail = null;
public Node flatten_APPROACH2(Node head) {
    if(head == null) return null;
    
    head.prev = tail;
    tail = head;
    
    Node nextNode = head.next;
    
    head.next = flatten(head.child);
    head.child = null;
    
    tail.next = flatten(nextNode);
    
    return head;
}

}