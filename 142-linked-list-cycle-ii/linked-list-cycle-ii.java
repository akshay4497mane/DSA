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
Approach: 
Take 1 loop : slow, fast(double speed) pointers
When they meet...Again start another loop untill head == slow | increment both 1 step at a time

Time  Complexity: O(N)
Space Complexity: O(1)
*/
    public ListNode detectCycle(ListNode head) {
        if(head == null)return null;
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if( slow == fast ) //cycle found
                break;
        }
        if( fast == null || fast.next == null) return null;
        while(head != slow){
            head = head.next;
            slow = slow.next;
        }
        return head;
    }
}