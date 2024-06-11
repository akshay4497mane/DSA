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
/*
Approach 3 : 
Traverse both a,b untill a!=b, 
but when ONE(eg. a ) goes to null reset it back to OTHER(headB)
Answer in two passes
https://leetcode.com/problems/intersection-of-two-linked-lists/solutions/49785/java-solution-without-knowing-the-difference-in-len
 */
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    ListNode a = headA, b = headB;
    while( a !=b ){
        a = (a != null)? a.next : headB;
        b = (b != null)? b.next : headA;
    }
    return a;
}

/*
Approach 2 : 
Find lenA, Find lenB, 
traverse longer list untill lenA>lenB or vice versa, 
Traverse parallely both until equal node is found
*/
public ListNode getIntersectionNodeByFindLen(ListNode headA, ListNode headB) {
    int lenA = getLength(headA), lenB = getLength(headB);
    for( ; lenA > lenB; headA = headA.next, lenA--);//stop when lenA=lenB
    for( ; lenB > lenA; headB = headB.next, lenB--);//Only 1 for loop will execute    
    while( headA != null && headB != null ){
        if(headA==headB) return headA;
        headA=headA.next; headB=headB.next;
    }
    return null;
}
    int getLength(ListNode head){
        int length = 0;
        for(; head!=null ; head=head.next, length++);
        return length;
    }
/*
Approach 1 :
Traverse both parallely, Identify longer list, remaining nodes gives(lenA-lenB), Traverse longer list, now traverse both parallelly until they meet
*/
public ListNode getIntersectionNodeTWOTraversal(ListNode headA, ListNode headB) {
    ListNode A = headA, B = headB;
    while(A != null && B != null ){//traverse both till one become null
        if(A==B) return A;
        A = A.next; B = B.next;
    }
    if(A != null){//
        ListNode A2 = headA;
        while(A != null){//traverse remaining nodes
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