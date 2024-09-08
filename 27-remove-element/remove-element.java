class Solution {
/*
The approach removes occurrences of val from the array by using two pointers: i moves from left to right, stopping at val, while j moves from right to left, stopping at non-val. When both pointers stop, the elements are swapped, and the process continues until i exceeds j. The function returns the new length of the array after removing val.
*/
    public int removeElement(int[] A, int val) {
        int N = A.length;
        int i = 0; //moves left -> right | stops at finding val
        int j = N-1;//moves right-> left | stops at finding non val
        while( i <= j){
            while( i < N && A[i] != val ){ i++; }
            while( j >= 0 && A[j] == val ){ j--; }
            if( i < j ){
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                i++; j--;
            }
        }
        return j+1;        
    }
}