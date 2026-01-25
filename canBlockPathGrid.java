/*
Problem (3–4 lines)
You are given an m×n grid where 0 means empty and 1 means stone (blocked).
A player starts at top-left (0,0) and wants to reach bottom-right (m−1,n−1).
The player can move only right or down and cannot pass through stones.
Q: Determine if placing exactly one additional stone (on an empty cell) can block all possible paths?

Key clarification (important in interview)
• Assume initially there exists at least one valid path.
• You are allowed to place the stone anywhere except start and end.
• Return true if one stone can block all paths, else false.

Revision Notes (read this first)
• Grid is a DAG (only right and down moves).
• Player can reach end if at least one path exists.
• Goal: check whether placing exactly one stone can block all paths.
• Key insight: if a cell lies on every start→end path, blocking it blocks the destination.

Important ideas to remember
• Brute force = try blocking each cell and re-check reachability
• DP path counting = identify a single point of failure
• dp1[i][j] × dp2[i][j] = number of paths passing through (i,j)
• If that equals totalPaths → that cell is unavoidable
*/

/*
Approach 1: Brute Force (Try placing stone at every empty cell)
Summary / Intuition
Try blocking each empty cell (except start/end) and check if any path remains.
Correct but slow; shows baseline thinking.

Time Complexity: O((m·n)²)
Space Complexity: O(m·n)

*/

class SolutionBrute {
    int m, n;

    public boolean canBlockPath(int[][] grid) {
        m = grid.length; n = grid[0].length;

        if (!hasPath(grid)) return true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && !(i == 0 && j == 0) && !(i == m - 1 && j == n - 1)) {
                    grid[i][j] = 1;
                    if (!hasPath(grid)) {
                        grid[i][j] = 0;
                        return true;
                    }
                    grid[i][j] = 0;
                }
            }
        }
        return false;
    }

    private boolean hasPath(int[][] g) {
        boolean[][] dp = new boolean[m][n];
        if (g[0][0] == 1) return false;
        dp[0][0] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 1) continue;
                if (i > 0) dp[i][j] |= dp[i - 1][j];
                if (j > 0) dp[i][j] |= dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
```

---

Approach 2: DP Path Counting (Optimized, Interview-Ready)

Summary / Intuition
Count number of paths from start to each cell and from each cell to end.
If a cell lies on all paths, blocking it blocks the player.

Time Complexity: O(m·n)
Space Complexity: O(m·n)

```java
class SolutionDP {
    public boolean canBlockPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long[][] dp1 = new long[m][n];
        long[][] dp2 = new long[m][n];

        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) return true;

        dp1[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) continue;
                if (i > 0) dp1[i][j] += dp1[i - 1][j];
                if (j > 0) dp1[i][j] += dp1[i][j - 1];
            }
        }

        dp2[m - 1][n - 1] = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 1) continue;
                if (i < m - 1) dp2[i][j] += dp2[i + 1][j];
                if (j < n - 1) dp2[i][j] += dp2[i][j + 1];
            }
        }

        long totalPaths = dp1[m - 1][n - 1];
        if (totalPaths == 0) return true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && !(i == 0 && j == 0) && !(i == m - 1 && j == n - 1)) {
                    if (dp1[i][j] * dp2[i][j] == totalPaths)
                        return true;
                }
            }
        }
        return false;
    }
}
```

---

Approach 3: Graph Cut Interpretation (Conceptual, Senior-Level)

Summary / Intuition
Grid is a DAG; we need to know if a single node disconnects source and target.
Equivalent to checking if max node-disjoint paths ≤ 1.

Practical note
Interviewers usually accept DP path-count solution; full max-flow is overkill.

Time Complexity: O(m·n)
Space Complexity: O(m·n)

---

When to say what in interview

• Start with brute force → correctness
• Move to DP paths → optimization + insight
• Mention cut-vertex interpretation → depth

---

Key one-liners to remember

• “This reduces to finding a single unavoidable cell.”
• “dp1 × dp2 equals totalPaths means every path passes through that cell.”
• “Right/down grid forms a DAG, so path counting works cleanly.”

---

Related Problems

• LeetCode 63 – Unique Paths II
[https://leetcode.com/problems/unique-paths-ii/](https://leetcode.com/problems/unique-paths-ii/)

• GFG – Grid path blocking (variations exist)

Exact problem not directly available, but commonly asked in interviews
