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
    //Recursive
    public ListNode reverseListRecursive(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode ans = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return ans;        
    }
    //Iterative Using Stack Space : O(N)
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        Stack<ListNode> st = new Stack<>();
        ListNode curr = head;
        while(curr != null){
            st.push(curr);
            curr = curr.next;
        }
        ListNode dummy = new ListNode(0);
        curr = dummy;
        while(st.size() > 0){   //(!st.isEmpty())
            curr.next = st.pop();
            curr = curr.next;
        }
        curr.next = null;
        return dummy.next;
    }

    //iterative Space : O(1) memory | Reverse LL
    public ListNode reverseListIterative(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode prev = null, curr = head, next = head.next;
        while(curr != null){
            curr.next = prev;
            prev = curr;
            curr = next;
            if(next != null)
                next = next.next;
        }
        return prev;        
    }
}