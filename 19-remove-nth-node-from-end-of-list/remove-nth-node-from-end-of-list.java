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
    //Approach 3 : Count all, traverse count-n | Time: O(N), 2 passes
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode curr = head, dummy = new ListNode(0, head);
        int N = 0;
        while(curr != null){ N++; curr = curr.next; }        
        curr = dummy;
        for(int i=0; i< N-n ; i++){ //N=5, n=1-5,
            curr = curr.next;
        }
        if(curr == dummy) return head.next;
        curr.next = curr.next.next;
        return head;
    }
    //Approach 4 : two pointer, move fast pointer n places ahead, traverse both equally | Time O(N) 1 pass
    public ListNode removeNthFromEndTwoPointer(ListNode head, int n) {
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