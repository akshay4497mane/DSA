/*
https://www.interviewbit.com/problems/k-largest-elements/
Problem Description
Given an 1D integer array A of size N you have to find and return the B largest elements of the array A.
NOTE:
Return the largest B elements in any order you like.

Problem Constraints
1 <= N <= 105
1 <= B <= N
1 <= A[i] <= 103

Input Format
First argument is an 1D integer array A

Second argument is an integer B.

Output Format
Return a 1D array of size B denoting the B largest elements.
*/

public class Solution {
    public int[] solve(int[] A, int B) {
        int[] ans = new int[B];
        int N = A.length;
/*
        //Approach 1 : O(nlogn) Sort, return last B elements
        Arrays.sort( A );
        if(B <= 0 || N <= 0 || B > N )
            return ans;
        int ansIndex = 0;
        for( int i =  N-1 ; i >= N-B ; i-- ){
            ans[ ansIndex++ ] = A[i] ;
        }                    
        return ans;
*/
        //Approach 2 :Time: O( N log B),Space: O( B )  Add B elements in minHeap, Do add other elements if elements are greater than minimum
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int i = 0;
        for( ; i< B; i++) //O(B log B)
            pq.add( A[i] );
        for(; i< N ; i++){ // O( N-B log B)
            if ( A[i] > pq.peek() ){
                pq.remove();
                pq.add(A[i]);
            }
            //System.out.println(pq.toString());
        }
        ans = pq.stream().mapToInt( Integer::intValue ).toArray();
        return ans;
    }
    
	static void printKLargestElements(int[] arr, int K) {
		/* APPROACH 3 : O( N + MaxElement)
		   Counting Sort Approach
		   Find MAX, create frequency array, Calculate each freq, iterate from last, until K max elements are fetched
		 */
		int maxElement = Integer.MIN_VALUE;
		for (int ele : arr) 		//Find Maximum Element
			maxElement = Math.max(ele, maxElement);
		
		int[] freq = new int[maxElement + 1];//Array of FREQUENCY, intialize to 0
		Arrays.fill(freq, 0);
		for (int ele : arr)
			freq[ele]++;
		//System.out.println("Freq: " + Arrays.toString(freq));
		int count = 0;
		for (int i = freq.length-1 ; i > 0 ; i--) {  //iterate FREQ in reverse
			for( int j = 0; j < freq[i] ; j++)
				if(count++ < K)
					System.out.print(i);
		}
	}
}
