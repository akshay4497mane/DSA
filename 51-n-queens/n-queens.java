/*
Approach :
Backtracking(row)

Maintain sets ColSet, PosiDiagSet, NegDiagSet -> to mark if already placed
*/
class Solution {
    int N;
    char[][] board;
    List<List<String>> ans;
    Set<Integer> colSet = new HashSet<>();
    Set<Integer> posDiagSet = new HashSet<>();
    Set<Integer> negDiagSet = new HashSet<>();
    public List<List<String>> solveNQueens(int n) {
        N = n;
        board = new char[n][n];
        for(int j=0; j<n; j++) Arrays.fill(board[j], '.');
        ans = new ArrayList<>();
        backtrack(0);
        return ans;
    }
    void backtrack( int r ){
        if(r==N){
            List<String> currAns = new ArrayList<>();
            for(char[] chArr : board){
                currAns.add(new String(chArr));
            }
            ans.add(currAns);
        }
        for(int c=0; c<N; c++){
            if(colSet.contains(c) || posDiagSet.contains(r+c) || negDiagSet.contains(r-c))
                continue;
            board[r][c] = 'Q';
            colSet.add(c);
            posDiagSet.add(r+c);
            negDiagSet.add(r-c);
            backtrack(r+1);
            board[r][c] = '.';
            colSet.remove(c);
            posDiagSet.remove(r+c);
            negDiagSet.remove(r-c);
        }
    }
}