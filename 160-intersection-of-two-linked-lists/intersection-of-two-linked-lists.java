/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {

    //Approach 1 : Traverse both parallely, Identify longer list, remaining nodes gives(lenA-lenB), Traverse longer list, now traverse both parallelly until they meet
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA, B = headB;
        while(A != null && B != null ){
            if(A==B) return A;
            A = A.next; B = B.next;
        }
        if(A != null){
            ListNode A2 = headA;
            while(A != null){
                A = A.next; A2 = A2.next;
            }
            B = headB;
            while(A2 != null && B!= null){
                if(A2==B) return A2;
                A2=A2.next; B=B.next;
            }
        }else if( B != null ){
            ListNode B2 = headB;
            while(B != null){
                B = B.next; B2 = B2.next;
            }
            A = headA;
            while(B2 != null && A!= null){
                if(B2==A) return B2;
                B2=B2.next; A=A.next;
            }
        }
        return null;
    }
}