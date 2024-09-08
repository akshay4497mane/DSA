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

    /*
This approach uses Floyd's Cycle Detection Algorithm or the Tortoise and Hare Algorithm.
We use two pointers: slow (one step at a time) and fast (two steps at a time).
If the list has a cycle, the pointers will meet; otherwise, the fast pointer will reach the end.
- Time Complexity: O(n)  
- Space Complexity: O(1)
*/
    public boolean hasCycle(ListNode head) {
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