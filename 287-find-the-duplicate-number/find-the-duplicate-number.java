class Solution {
/*
Approach 1 : Sort array, compare ith & i-1 th elements  | Time: O(N logN) | Space: O(1)
Approach 2 : Count frequency of each | O(N) time but using O(N) space, boolean Array, HashMap, HashSet |  
Approach 3 : Brute Force, Use two loops, Search each element in array, O(N^2) time
*/
    //Approach 1 : Sort array, compare ith & i-1 th elements  | Time: O(N logN) | Space: O(1)
    public int findDuplicate_Sorting(int[] A) {
        Arrays.sort(A);
        int N = A.length;
        for(int i=1; i<N ;i++)
            if(A[i-1]==A[i])
                return A[i];
        return N;
    }
    //
    public int findDuplicate(int[] A) {
        int N = A.length;
        boolean[] visited = new boolean[N+1];
        for(int i=0;i<N;i++){
            if(visited[A[i]] == true)
                return A[i];
            visited[A[i]] = true;
        }
        return N;
    }

}