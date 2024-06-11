/*
Recursive Solution: (difficult to understand)
https://www.geeksforgeeks.org/flattening-a-linked-list/
My Solution Iterative:
https://www.geeksforgeeks.org/problems/flattening-a-linked-list/1

Given a Linked List of size N, where every node represents a sub-linked-list and contains two pointers:
(i) a next pointer to the next node,
(ii) a bottom pointer to a linked list where this node is head.
Each of the sub-linked-list is in sorted order.
Flatten the Link List such that all the nodes appear in a single level while maintaining the sorted order. 
Note: The flattened list will be printed using the bottom pointer instead of the next pointer.
For more clarity have a look at the printList() function in the driver code.
Example 1:
Input:
5 -> 10 -> 19 -> 28
|     |     |     | 
7     20    22   35
|           |     | 
8          50    40
|                 | 
30               45
Output:  5-> 7-> 8- > 10 -> 19-> 20->
22-> 28-> 30-> 35-> 40-> 45-> 50.
Explanation:
The resultant linked lists has every 
node in a single level.
(Note: | represents the bottom pointer.)
 

Example 2:

Input:
5 -> 10 -> 19 -> 28
|          |                
7          22   
|          |                 
8          50 
|                           
30              
Output: 5->7->8->10->19->22->28->30->50
Explanation:
The resultant linked lists has every
node in a single level.

(Note: | represents the bottom pointer.)
 

Your Task:
You do not need to read input or print anything. Complete the function flatten() that takes the head of the linked list as input parameter and returns the head of flattened link list.

Expected Time Complexity: O(N*N*M)
Expected Auxiliary Space: O(N)

Constraints:
0 <= N <= 50
1 <= Mi <= 20
1 <= Element of linked list <= 103
*/



//{ Driver Code Starts
import java.util.Scanner;
import java.util.*;
import java.io.*;

class Node
{
	int data;
	Node next;
	Node bottom;
	
	Node(int d)
	{
		data = d;
		next = null;
		bottom = null;
	}
}


class Flatttening_A_LinkedList
{	
    Node head;
	
	void printList(Node node)
    {
        //Node temp = head;
        while (node != null)
        {
            System.out.print(node.data + " ");
            node =node.bottom;
        }
        System.out.println();
    }
	public  static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		Flatttening_A_LinkedList list = new Flatttening_A_LinkedList();
		while(t>0)
		{
			int N = sc.nextInt();
			int arr[] = new int[N];
			for(int i=0;i<N;i++)
				arr[i] = sc.nextInt();
			
			Node temp = null;
			Node tempB = null;
			Node pre = null;
			Node preB = null;	
			int flag=1;
			int flag1=1;
			for(int i=0; i<N;i++)
			{
				int m = arr[i];
				m--;
				int a1 = sc.nextInt();
				temp = new Node(a1);
				if(flag == 1)
				{
					list.head = temp;
					pre = temp;
					flag = 0;
					flag1 = 1;
				}
				else
				{
					pre.next = temp;
					pre = temp;
					flag1 = 1;
				}
				
				for(int j=0;j<m;j++)
				{
					int a = sc.nextInt();
					tempB = new Node(a);
					if(flag1 == 1)
					{
						temp.bottom = tempB;
						preB = tempB;
						flag1 = 0;
					}
					else
					{
						preB.bottom = tempB;
						preB = tempB;
					}
				}
				
			}
			//list.printList();
			GfG g = new GfG();
			Node root = g.flatten(list.head);
			list.printList(root);
		
		t--;
		}
	}	
}
// } Driver Code Ends


/*Node class  used in the program
class Node
{
	int data;
	Node next;
	Node bottom;
	
	Node(int d)
	{
		data = d;
		next = null;
		bottom = null;
	}
}
*/
/*  Function which returns the  root of 
    the flattened linked list. */
class GfG{
    Node flatten(Node root){
        while(root != null && root.next!=null){
            Node Next = root.next.next;
            root = merge(root, root.next);
            root.next = Next;
        }
        return root;
    }
    Node merge(Node headA, Node headB){
        Node a = headA, b = headB;
        Node dummyAns = new Node(0), dummyHead = dummyAns;
        while(a!=null && b!=null){
            if(a.data <= b.data){
                dummyAns.bottom = a;
                a=a.bottom;
            }else{
                dummyAns.bottom = b;
                b=b.bottom;
            }
            dummyAns = dummyAns.bottom;            
        }
        if(a!=null){
            dummyAns.bottom = a;
        }
        if(b!=null){
            dummyAns.bottom = b;
        }
        return dummyHead.bottom;
    }
}

//APPROACH 2 : RECURSION

// Java program for flattening a Linked List
class LinkedList {
	Node head; // head of list

	/* Linked list Node*/
	class Node {
		int data;
		Node next, bottom;
		Node(int data)
		{
			this.data = data;
			next = null;
			bottom = null;
		}
	}

	// An utility function to merge two sorted linked lists
	Node merge(Node a, Node b)
	{
		// if first linked list is empty then second
		// is the answer
		if (a == null)
			return b;

		// if second linked list is empty then first
		// is the result
		if (b == null)
			return a;

		// compare the data members of the two linked lists
		// and put the larger one in the result
		Node result;

		if (a.data < b.data) {
			result = a;
			result.bottom = merge(a.bottom, b);
		}

		else {
			result = b;
			result.bottom = merge(a, b.bottom);
		}

		result.next = null;
		return result;
	}

	Node flatten(Node root)
	{
		// Base Cases
		if (root == null || root.next == null)
			return root;

		// recur for list on next
		root.next = flatten(root.next);

		// now merge
		root = merge(root, root.next);

		// return the root
		// it will be in turn merged with its left
		return root;
	}

	/* Utility function to insert a node at beginning of the
	linked list */
	Node push(Node head_ref, int data)
	{
		/* 1 & 2: Allocate the Node &
				Put in the data*/
		Node new_node = new Node(data);

		/* 3. Make next of new Node as head */
		new_node.bottom = head_ref;

		/* 4. Move the head to point to new Node */
		head_ref = new_node;

		/*5. return to link it back */
		return head_ref;
	}

	void printList()
	{
		Node temp = head;
		while (temp != null) {
			System.out.print(temp.data + " ");
			temp = temp.bottom;
		}
		System.out.println();
	}

	// Driver's code
	public static void main(String args[])
	{
		LinkedList L = new LinkedList();

		/* Let us create the following linked list
			5 -> 10 -> 19 -> 28
			| |	 |	 |
			V V	 V	 V
			7 20 22 35
			|		 |	 |
			V		 V	 V
			8		 50 40
			|			 |
			V			 V
			30			 45
		*/

		L.head = L.push(L.head, 30);
		L.head = L.push(L.head, 8);
		L.head = L.push(L.head, 7);
		L.head = L.push(L.head, 5);

		L.head.next = L.push(L.head.next, 20);
		L.head.next = L.push(L.head.next, 10);

		L.head.next.next = L.push(L.head.next.next, 50);
		L.head.next.next = L.push(L.head.next.next, 22);
		L.head.next.next = L.push(L.head.next.next, 19);

		L.head.next.next.next
			= L.push(L.head.next.next.next, 45);
		L.head.next.next.next
			= L.push(L.head.next.next.next, 40);
		L.head.next.next.next
			= L.push(L.head.next.next.next, 35);
		L.head.next.next.next
			= L.push(L.head.next.next.next, 28);

		// Function call
		L.head = L.flatten(L.head);

		L.printList();
	}
} /* This code is contributed by Rajat Mishra */


