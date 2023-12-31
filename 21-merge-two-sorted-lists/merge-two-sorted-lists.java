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
    
    //Approach 1: Recursive
    public ListNode mergeTwoListsRECURSIVE(ListNode l1, ListNode l2){
		if(l1 == null) return l2;
		if(l2 == null) return l1;
		if(l1.val < l2.val){
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else{
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
    }
    /*Approach 2: Non Recursive 
    
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode ansHead= null, ans = null;
        if(list1==null) return list2;
        if(list2==null) return list1;
                
        if ( list1.val < list2.val ){
            ans = list1;
            list1 = list1.next;            
        }
        else{
            ans = list2;
            list2 = list2.next;            
        }
        ansHead = ans;
        
        while(list1 != null && list2!=null){
            if(list1.val < list2.val ){
                ans.next = list1;
                list1 = list1.next;
            }else{
                ans.next = list2;
                list2 = list2.next;                
            }
            ans = ans.next;
        }
        if(list1 != null ){
            ans.next = list1;
        }else{
            ans.next = list2;
        }
        return ansHead;
    }
    */
    //Approach 2: Non Recursive  + DUMMY NODE
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode prevAns, ans = new ListNode(0);
        prevAns = ans;
        if(list1==null) return list2;
        if(list2==null) return list1;

        while(list1 != null && list2!=null){
            if(list1.val < list2.val ){
                ans.next = list1;
                list1 = list1.next;
            }else{
                ans.next = list2;
                list2 = list2.next;                
            }
            ans = ans.next;
        }
        if(list1 != null ){
            ans.next = list1;
        }else{
            ans.next = list2;
        }
        return prevAns.next;
    }
}