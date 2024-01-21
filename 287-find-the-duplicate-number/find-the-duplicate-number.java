class Solution {
/*
Approach 1 : Sort array, compare ith & i+1th elements  | Time: O(N logN)
Approach 2 : Count frequency of each | O(N) time but using O(N) space, boolean Array, HashMap, HashSet |  
Approach 3 : Brute Force, Use two loops, Search each element in array, O(N^2) time
*/
    public int findDuplicate(int[] A) {
        Arrays.sort(A);
        int N = A.length;
        for(int i=1; i<N ;i++)
            if(A[i-1]==A[i])
                return A[i];
        return N;
    }
    

}