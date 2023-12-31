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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ansDummyHead = new ListNode(0), curr = ansDummyHead;
        int ans=0, carry = 0;
        while(l1!=null && l2!=null){
            ans = l1.val + l2.val + carry ;
            curr.next = new ListNode( ans % 10 );
            l1 = l1.next; l2 = l2.next;
            curr = curr.next;
            carry = ans / 10;
        }
        while(l1 != null){
            ans = l1.val + carry ;
            curr.next = new ListNode( ans % 10 );
            l1 = l1.next;
            curr = curr.next;
            carry = ans / 10;
        }
        while(l2 != null){
            ans = l2.val + carry ;
            curr.next = new ListNode( ans % 10 );
            l2 = l2.next;
            curr = curr.next;
            carry = ans / 10;
        }
        if(carry == 1){
            curr.next = new ListNode( 1 ); curr = curr.next;
        }
        curr.next = null;
        return ansDummyHead.next;
    }
}