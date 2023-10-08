/*
Given a linked list A, swap every two adjacent nodes and return its head.

NOTE: Your algorithm should use only constant space. You may not modify the values in the list; only nodes themselves can be changed.



Problem Constraints
 1 <= |A| <= 106 



Input Format
 The first and the only argument of input contains a pointer to the head of the given linked list. 



Output Format
 Return a pointer to the head of the modified linked list. 



Example Input
 Input 1: 

 A = 1 -> 2 -> 3 -> 4
 Input 2: 

 A = 7 -> 2 -> 1


Example Output
 Output 1: 

 2 -> 1 -> 4 -> 3
 Output 2: 

 2 -> 7 -> 1
 */

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     public int val;
 *     public ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
public class Solution {
    public ListNode swapPairs(ListNode A) {
        ListNode Head = null, thirdNode = null, secondNode = null, beforeA = null;
        if(A == null || A.next == null)
            return A;

        
        while(A != null && A.next != null){
            thirdNode = A.next.next;
            secondNode = A.next;
            A.next = thirdNode;
            secondNode.next = A;
            if(beforeA != null)
                beforeA.next = secondNode;
            beforeA = A;
            if(Head == null)
                Head = secondNode;
            A = thirdNode;
        }
        return Head;
    }
}
