/*
Backtracking approach

time : O( R * C * 4^L) | L = length of word

*/

class Solution {
    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;

        boolean[][] visited = new boolean[rows][cols];

        // Try every cell as a starting point
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfs(board, word, i, j, 0, visited))
                    return true;
            }
        }
        return false;
    }

    private boolean dfs(
            char[][] board,
            String word,
            int r,
            int c,
            int index,
            boolean[][] visited) {

        // All characters matched
        if (index == word.length())
            return true;

        // Boundary check
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length)
            return false;

        // Already used or mismatch
        if (visited[r][c] || board[r][c] != word.charAt(index))
            return false;

        // Mark visited
        visited[r][c] = true;

        // Explore all 4 directions
        boolean found =
                dfs(board, word, r + 1, c, index + 1, visited) ||
                dfs(board, word, r - 1, c, index + 1, visited) ||
                dfs(board, word, r, c + 1, index + 1, visited) ||
                dfs(board, word, r, c - 1, index + 1, visited);

        // Backtrack
        visited[r][c] = false;

        return found;
    }
}