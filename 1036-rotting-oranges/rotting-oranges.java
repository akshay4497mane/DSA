class Solution {
    public int orangesRotting(int[][] grid) {
        Queue<Pair<Integer, Integer>> q = new ArrayDeque<>();
        int rows = grid.length, cols = grid[0].length;
        int freshOranges = 0;
        for( int r=0; r<rows; r++ ){
            for( int c=0; c<cols ; c++ ){
                if( grid[r][c] == 2 ){
                    q.add( new Pair(r,c));
                }else if(grid[r][c] == 1){
                    freshOranges++;
                }
            }
        }
        q.add(new Pair(-1,-1));
        //Start Rotting
        int[][] directions = {{-1,0}, {0,1}, {1,0}, {0,-1}};
        int minutesElapsed = -1;
        while( !q.isEmpty() ){
            Pair<Integer, Integer> p = q.remove();
            int row = p.getKey();
            int col = p.getValue();
            if( row == -1 ){
                minutesElapsed++;
                if(!q.isEmpty())
                    q.add(new Pair(-1,-1));
            }else{//rott all neighbours
                for(int[] d : directions){
                    int neighbourRow = row + d[0];
                    int neighbourCol = col + d[1];
                    if( isValid(neighbourRow,neighbourCol, rows, cols) ){
                        if(grid[neighbourRow][neighbourCol] == 1){
                            grid[neighbourRow][neighbourCol] = 2;
                            freshOranges--;
                            q.add(new Pair(neighbourRow, neighbourCol));
                        }
                    }                  
                }
            }
        }
        return freshOranges == 0 ? minutesElapsed : -1;
    }
    boolean isValid(int r,int c, int rows, int cols){
        if(r >= 0 && r < rows && c >= 0 && c < cols){
            return true;
        }else{
            return false;
        }
    }
}