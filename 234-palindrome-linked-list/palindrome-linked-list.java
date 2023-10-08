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
    public boolean isPalindrome(ListNode head) {
        /*
        APPROACH 1 : Find Middle, Reverse list 2, compare both
        Time: O(N), Space: O(1)
        */
        if(head == null || head.next == null)
            return true;
        //Find Middle 
        ListNode slow = head, fast = head.next.next;
        while( fast != null && fast.next != null ){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode list1 = head, list2 = slow.next;
        slow.next = null;
        
        //Reverse list 2
        ListNode revList2 = reverseLinkedList(list2);

        //compare list1, reversed list2
        while(list1 != null && revList2 != null){
            if( list1.val != revList2.val )
                return false;
            list1 = list1.next;
            revList2 = revList2.next;
        }
        return true;
    }
    ListNode reverseLinkedList(ListNode head){
        if(head == null || head.next == null)
            return head;
        ListNode ans = reverseLinkedList(head.next);
        head.next.next = head;
        head.next = null;
        return ans;
    }
}