/*
Matrix Search
Programming
Binary Search
Medium
35.9% Success

231

14

Bookmark
Asked In:
Problem Description
 
 

Given a matrix of integers A of size N x M and an integer B. Write an efficient algorithm that searches for integer B in matrix A. 

This matrix A has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than or equal to the last integer of the previous row.
Return 1 if B is present in A, else return 0.

NOTE: Rows are numbered from top to bottom, and columns are from left to right.



Problem Constraints
1 <= N, M <= 1000

1 <= A[i][j], B <= 106



Input Format
The first argument given is the integer matrix A.

The second argument given is the integer B.



Output Format
Return 1 if B is present in A else, return 0.



Example Input
Input 1: 

A = [ 
      [1,   3,  5,  7]
      [10, 11, 16, 20]
      [23, 30, 34, 50]

    ]
B = 3
Input 2:

A = [

      [5, 17, 100, 111]
      [119, 120, 127, 131]

    ]
B = 3


Example Output
Output 1: 

1
Output 2:

0


Example Explanation
Explanation 1: 

 3 is present in the matrix at A[0][1] position so return 1.
Explanation 2:

 3 is not present in the matrix so return 0.
*/
//Approach 1: Using Binary search, Recursion, separate function
//Time: O(M*N log (M*N)), Space : O(1)

public class Solution {
    public int binarySearch(ArrayList<Integer> A, int target, int start, int end){
        //System.out.println("Start:" + start + "End:" + end);
        if(start > end)
            return 0;
        if(target < A.get(start) || target > A.get(end))
            return 0;
        int mid = (start + end)/2;
        if(target == A.get(mid))
            return 1;
        return binarySearch(A, target, start, mid) + binarySearch(A, target, mid +1, end);
    }
    
    public int searchMatrix(ArrayList<ArrayList<Integer>> A, int B) {
        int numRows = A.size() , target = B;
        for(int i = 0 ; i < numRows ; i++){
            int found = binarySearch(A.get(i), target, 0, A.get(i).size()-1 );
            if(found == 1)
                return 1;
        }
        return 0;
    }
}

//Approach 2 : Binary Search with min-max index, Assume Array length = M*N  
//Time: O(M*N log (M*N)), Space : O(1)
public class Solution {
	public int searchMatrix(ArrayList<ArrayList<Integer>> a, int b) {
	    if(a.size() < 1) 
            return 0;
	    
	    int yLength = a.get(0).size();
	    
	    int max = (a.size() * yLength) - 1;
	    int min = 0;
	    int mid;
	    int x;
	    int y;
	    while(min <= max){
	        mid = min + ((max - min)/2);
	        x = mid / yLength;
	        y = mid % yLength;
	        if(a.get(x).get(y) == b){
	            return 1;
	        }else if (a.get(x).get(y) > b){
	            max = mid - 1;
	        }else{
	            min = mid + 1;
	        }
	    }	    
	    return 0;	    
	}
}


