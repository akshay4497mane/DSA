/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
/* Approach 1 :
This approach uses a HashSet to track visited nodes. As we traverse the list, we check if the current node has been visited before. If it has, a cycle exists. Otherwise, we add the node to the set and continue.
- Time Complexity: O(n) — Each node is visited once.
- Space Complexity: O(n) — The HashSet stores up to *n* nodes in the worst case (if no cycle exists).
*/
    public boolean hasCycle(ListNode head) {
        Set<ListNode> visited = new HashSet<>();
        while( head != null ){
            if( visited.contains(head) ) return true; //found cycle
            visited.add(head);
            head = head.next;
        }
        return false;
    }

    /* Approach 2:
This approach uses Floyd's Cycle Detection Algorithm or the Tortoise and Hare Algorithm.
We use two pointers: slow (one step at a time) and fast (two steps at a time).
If the list has a cycle, the pointers will meet; otherwise, the fast pointer will reach the end.
- Time Complexity: O(n)  
- Space Complexity: O(1)
*/
    public boolean hasCycle_approach2(ListNode head) {
        if ( head == null || head.next == null ) return false;
        ListNode slow = head.next, fast = head.next.next;
        while( fast != null && fast.next != null && slow != fast ){
            slow = slow.next;
            fast = fast.next.next;
        }
        if( slow == fast ) return true;
        return false;
    }
}