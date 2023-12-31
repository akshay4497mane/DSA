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
    //Approach 1 : Using Recursion, 
    //Approach 2 : Using Stack, 
    //Approach 3 : Count all, traverse count-n
    
    //Approach 4 : two pointer, move fast pointer n places ahead, traverse both equally
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head, fast = head, prev = new ListNode(0, head);
        for(; n>0 ; n--){
            fast = fast.next;
        }
        while(fast != null){
            prev = prev.next;
            fast = fast.next;
        }
        if(prev.next == head)
            return head.next;
        prev.next = prev.next.next;
        return head;
    }

}