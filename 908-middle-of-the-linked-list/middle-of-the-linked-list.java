/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    //Approach 1: Calculate N, traverse N/2, Time: O(N), 2 passes, Space: O(1)
    public ListNode middleNode2Pass(ListNode head) {
        if(head == null || head.next == null) return head;
        int N=0; ListNode curr = head;
        while( curr != null ){      //Find count N
            N++; curr = curr.next;
        }
        N = N/2; curr = head;
        while( N > 0 ){  //Traverse N/2 mid
            N--; curr = curr.next;
        }
        return curr;
    }

    //Approach 2: TWO Pointers, Time: O(N), 1 pass, Space: O(1)
    public ListNode middleNode(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null ){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}