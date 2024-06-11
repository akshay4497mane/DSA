class Solution {
    public void nextPermutation( int[] A ) {
        int n=A.length;
        //2 1 5 4 3 0 0
        //Step 1 : Traverse from right, find DIP, index i : A[i] < A[i+1]  ( Eg. i -> 1)
        int i = n-2;
        for( ; i>=0; i--)
            if(A[i] < A[i+1])
                break;
        int j = n-1;
        if( i != -1){// if i== -1; skip and just reverse entire array
            //step 2: Traverse from right, find j : A[j] > A[i] ( Eg. j -> 3
            for( ; j>i ; j--)
                if( A[j] > A[i] )
                    break;
            //swap A[i], A[j]  | Eg. 2 3-5 4 1 0 0
            int temp = A[i]; A[i] = A[j]; A[j] = temp;
        }
        //reverse array from i+1 to n-1 | Eg: 2 3-0 0 1 4 5
        i++; j = n-1;
        while(i<j){
            int temp = A[i]; A[i] = A[j]; A[j] = temp;
            i++;j--;            
        }
    }
}