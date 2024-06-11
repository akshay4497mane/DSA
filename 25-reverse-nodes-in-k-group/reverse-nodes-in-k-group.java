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
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null) return head;
            int oldK = k, count=0;
        ListNode node = head;
        while( count < k ){ //check if atleast K nodes exists
            if( node == null ) return head;
            node = node.next;
            count++;
        }

        ListNode prev = null, curr = head, nxt = head.next;
        while( k > 0 && curr != null){ //reverse K nodes
            nxt = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nxt;
            k--;
        }
        if(curr != null )
            head.next = reverseKGroup( curr, oldK ); //last element of new K size list
        return prev; //answer : 1st element of new K list 
    }
}