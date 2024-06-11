class Solution {
    /*
    APPROACH : 
    Finding Unsurrounded regions will be easier than surrounded ones as we know start point.
    Start point will be any boundary row/col.
    step1: DFS start from all boundary row/columns => Convert all 'O' to '*'
    step2: use two for loops, convert all "O" to X,   
    step3: convert all '$'' back to 'O'
    */
    public void solve(char[][] board) {
    //step1: DFS start from all boundary row/columns => Convert all 'O' to '*'
        int R = board.length, C = board[0].length;
        for(int i=0 ; i<R ;i++){
            if( board[i][0] == 'O' ){ //0th column
                dfs( i, 0, board);
            }
            if( board[i][C-1] == 'O' ){ //Last column
                dfs( i, C-1, board);
            }
        }
        for(int j=0 ; j<C ;j++){
           if( board[0][j] == 'O' ){ //0th column
                dfs( 0, j, board);
            }
            if( board[R-1][j] == 'O' ){ //Last column
                dfs( R-1, j, board);
            }
        }

    //step2: use two for loops, convert all "O" to X,   
    //step3: convert all '$'' back to 'O'
        for(int i=0; i<R ;i++){
            for(int j=0; j<C ;j++){
                if( board[i][j] == 'O')
                    board[i][j] = 'X';
                else if( board[i][j] == '$' )
                    board[i][j] = 'O';
            }
        }
    }
    void dfs(int r, int c, char[][] board){
        int R = board.length, C = board[0].length;
        if( r<0 || r> R-1 || c<0 || c>C-1 || board[r][c] != 'O') return;
        board[r][c] = '$';
        dfs(r-1, c, board);
        dfs(r+1, c, board);
        dfs(r, c-1, board);
        dfs(r, c+1, board);
    }
}