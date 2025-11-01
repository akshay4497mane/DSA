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
    public ListNode modifiedList_1(int[] nums, ListNode head) {
        Set<Integer> numSet = new HashSet<>();
        for(int num : nums){
            numSet.add(num);
        }
        ListNode headBackup = head;
        for(ListNode node = head, prevNode = null; node != null; node = node.next){
            if(numSet.contains(node.val)){
                if(prevNode == null){
                    headBackup = node.next;
                }else{
                    prevNode.next = node.next; //delete node
                }
            }else{
                prevNode=node;
            }
        }
        return headBackup;
    }
    /*Approach 2 : Using DUMMY Head
    Time complexity: O(m+n)
    Space complexity: O(m)
    */
    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) numSet.add(num);
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = head, prev = dummy;

        while (curr != null) {
            if (numSet.contains(curr.val)) {
                prev.next = curr.next; // remove node
            } else {
                prev = curr; // move forward
            }
            curr = curr.next;
        }
        return dummy.next;
    }
}