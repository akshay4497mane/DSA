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
    public ListNode middleNode(ListNode head) {        
        if(head == null || head.next == null) return head;
        //Find count N
        int N=0;
        ListNode curr = head;
        while( curr != null ){
            N++; curr = curr.next;
        }
        N = N/2; curr = head;
        while( N > 0 ){
            curr = curr.next;
            N--; 
        }
        return curr;
    }
}