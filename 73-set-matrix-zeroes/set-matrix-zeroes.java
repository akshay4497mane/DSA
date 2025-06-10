class Solution {
    public void setZeroes(int[][] A) {
        int R = A.length;
        int C = A[0].length;
        Boolean isFirstCol = false;
        for(int i=0; i<A.length; i++){
            if(A[i][0]==0)
                isFirstCol = true;
            for(int j=1; j<A[0].length; j++){
                if(A[i][j]==0){
                    A[i][0] = 0;
                    A[0][j] = 0;
                }                
            }
        }

        //process first column of every row for every 0
        for(int i=1; i<A.length; i++){
            if(A[i][0]==0){
                for(int j=1; j<A[0].length; j++){
                    A[i][j] = 0;
                }
            }
        }
        //process first row of all columns for every 0
        for(int j=1; j<A[0].length; j++){
            if(A[0][j]==0){
                for(int i=1; i<A.length; i++){
                    A[i][j] = 0;
                }
            }
        }
        if(A[0][0]==0){//First row is zero
            for(int j=1; j<A[0].length; j++)
                A[0][j] = 0;   
        }
        if(isFirstCol)
            for(int i=0; i<A.length; i++)
                A[i][0] = 0;
    }
}
/*
Edge Case 1 : 
[[1,0]]

Edge Case 2: 
    [1, 2, 3, 4],
    [5, 0, 7, 8],
    [0,10,11,12],
    [13,14,15,0]
*/