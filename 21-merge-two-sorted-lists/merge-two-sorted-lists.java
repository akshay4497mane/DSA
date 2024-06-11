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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode i = list1, j = list2, ans = new ListNode(), dummyHead=ans;
        while( i != null && j != null){
            if(i.val < j.val){
                ans.next = i;
                i = i.next;
            }else{
                ans.next = j;
                j = j.next;
            }
            ans = ans.next;
        }
        if( i != null ){
            ans.next = i;
        }else{
            ans.next = j;
        }
        return dummyHead.next;
    }
}