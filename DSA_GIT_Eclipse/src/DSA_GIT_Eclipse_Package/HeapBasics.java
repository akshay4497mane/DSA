package DSA_GIT_Eclipse_Package;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class HeapBasics {
	
	public static void main(String[] args) {
		int[] arr = {1,3,4,5,5,5,8};

		//heapSort(heap);		
		heapSortPriorityQueue(arr, "Reverse");
		System.out.println("After sort(): " + Arrays.toString(arr));
	}
	static void heapSortPriorityQueue(int[] heap, String flag) {
		int N = heap.length;
		PriorityQueue<Integer> pq = new PriorityQueue<>(); //Normal Increasing Order
		if( flag.equals("Reverse"))
			pq = new PriorityQueue<>( Collections.reverseOrder() ); //Reverse Order
		for (Integer ele : heap) { //Add elements into Heap -> N log(N)
			pq.add(ele);			
		}
		for(int i =0 ; i<N ; i++) //Remove and heapify() => N log N
			heap[i] = pq.remove();
	}
	
	public static void buildHeap(int[] heap) {
		//Convert array -> Heap
		int N = heap.length;
		//Recursive call to heapify() to all NON LEAF Nodes
		for(int nonleaf = N/2-1; nonleaf >=0 ; nonleaf-- )
			heapify(heap, N , nonleaf);
	}
	public static void heapSort(int[] heap) {  //O N log(N)
		int N = heap.length;
		//Step 1 : Build Max Heap
		buildHeap(heap);
		//Extract max Root and store at end, Call Heapify for root.
		while( N != 0) {
			int largest_ele = heap[0];
			heap[0] = heap[N-1];
			heap[N-1] = largest_ele;
			N--;
			heapify(heap, N, 0);			
		}
	}
	public static void heapify( int[] heap, int size, int curr){
		int largest_index = curr;
		int left = 2 * curr + 1;
		int right = 2 * curr + 2;
		
		//Find largest between Root, left & root, right
		if( left < size && heap[left] > heap[largest_index] )
			largest_index = left;
		if( right < size && heap[right] > heap[largest_index] )
			largest_index = right;

		if( largest_index != curr) {
			//Swap Largest & current elements
			int large = heap[largest_index];
			heap[largest_index] = heap[curr];
			heap[curr] = large;
			//Recursive Call HEAPIFY() to largest L or R node
			heapify(heap, size, largest_index);
		}	
	}

}
