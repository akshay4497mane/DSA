class Solution {
    public void rotate(int[][] A) {
        //Step 1: Transpose Matrix, Swap A[i][j] & A[j][i]
        for(int i=0 ; i<A.length ; i++ ){ //Level of rotation
            for(int j=i; j<A[0].length ; j++ ){
                int temp = A[i][j]; A[i][j] = A[j][i]; A[j][i] = temp;
            }
        }
        //Step 2: Flip Matrix horzontally | Swap Columns 0 and last column | Swap A[i][j] ~ A[i][A[0].length-1-j]
        for(int i=0 ; i<A.length ; i++ ){ //For all ROWS
            for(int j=0; j<A[0].length/2 ; j++ ){//For HALF Columns (0 < j < N/2)
                int temp = A[i][j]; A[i][j]=A[i][A[0].length-1-j]; A[i][A[0].length-1-j] = temp;
            }
        }
    }
}