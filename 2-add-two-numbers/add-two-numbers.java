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
//Approach 1 : Using THREE LOOPS
    public ListNode addTwoNumbersApproach1(ListNode l1, ListNode l2) {
        ListNode ansDummyHead = new ListNode(0), curr = ansDummyHead;
        //use dummyNode to avoid handling Special HEAD condition in loop
        int ans=0, carry = 0;
        while(l1!=null && l2!=null){
            ans = l1.val + l2.val + carry ;
            curr.next = new ListNode( ans % 10 );
            l1 = l1.next; l2 = l2.next;
            curr = curr.next;
            carry = ans / 10;
        }
        while(l1 != null){ //Handle remaining elements in the FIRST linked list.
            ans = l1.val + carry ;
            curr.next = new ListNode( ans % 10 );
            l1 = l1.next;
            curr = curr.next;
            carry = ans / 10;
        }
        while(l2 != null){ //Handle remaining elements in the SECOND linked list.
            ans = l2.val + carry ;
            curr.next = new ListNode( ans % 10 );
            l2 = l2.next;
            curr = curr.next;
            carry = ans / 10;
        }
        if(carry == 1){// If carry after processing all elements, add a new node for it.
            curr.next = new ListNode( 1 ); 
            curr = curr.next;
        }
        curr.next = null;
        return ansDummyHead.next;
    }

//Approach 2 : Using ONE LOOPS
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ansDummyHead = new ListNode(0), curr = ansDummyHead;
        //use dummyNode to avoid handling Special HEAD condition in loop
        int sum=0, carry = 0;
        while(l1!=null || l2!=null || carry != 0){
            sum = ( (l1!=null) ? l1.val : 0 ) +
                  ( (l2!=null) ? l2.val : 0 ) + 
                  carry ;
            curr.next = new ListNode( sum % 10 );
            carry = sum / 10;

            l1 = (l1!=null) ? l1.next : null; 
            l2 = (l2!=null) ? l2.next : null;
            curr = curr.next;
        }
        curr.next = null;
        return ansDummyHead.next;
    }
}