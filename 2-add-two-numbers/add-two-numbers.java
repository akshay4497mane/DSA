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
    /*
- Dummy Node: `prevAns` simplifies list construction. It allows easy node addition and lets you return `prevAns.next` for the result.
- Loop Condition (`||`): Continues while either list has nodes, handling different lengths effectively.
- Carry Handling: Adds digits from both lists and manages carry-over, creating new nodes as needed.

- Complexity:
  - Time: O(max(n, m)) — where *n* and *m* are the lengths of the two lists.
  - Space: O(max(n, m)) — for the result list.    
*/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode prevAns = new ListNode(999, null), ans = prevAns;
        int carry = 0;
        while( l1 != null || l2 != null ){
            int sum = ((l1 != null)?l1.val:0)  + ((l2!=null)?l2.val:0) + carry;
            ans.next = new ListNode( sum % 10 );
            carry = sum / 10;
            ans = ans.next;
            l1 = (l1 != null)?l1.next:null;
            l2 = (l2 != null)?l2.next:null;
        }
        if( carry != 0){
            ans.next = new ListNode( carry );
        }
        return prevAns.next;
    }
}