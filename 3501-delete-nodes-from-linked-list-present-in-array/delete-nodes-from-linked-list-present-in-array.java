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
    public ListNode modifiedList(int[] nums, ListNode head) {
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
}