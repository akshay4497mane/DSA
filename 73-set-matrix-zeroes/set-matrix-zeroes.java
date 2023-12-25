class Solution {
    public void setZeroes(int[][] mat) {
        boolean flag0Row = false, flag0Col=false;
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat[0].length; j++){
                if(mat[i][j]==0){
                    mat[i][0] = 0;
                    mat[0][j] = 0;
                    if(i==0) flag0Row = true;
                    if(j==0) flag0Col = true;
                }
            }
        }
        for(int i=1;i<mat.length;i++){
            for(int j=1;j<mat[0].length; j++){
                if(mat[i][0]==0 || mat[0][j]==0)
                    mat[i][j] = 0;
            }
        }
        if( flag0Row == true)
            for(int j=0; j<mat[0].length; j++)
                mat[0][j] = 0;
        if( flag0Col == true)
            for(int i=0; i<mat.length; i++)
                mat[i][0] = 0;
    }
}