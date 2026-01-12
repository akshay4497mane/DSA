/*
Revision Notes – 920. Number of Music Playlists

Problem in one line
Count playlists of length goal using n songs, every song used at least once, and a song can repeat only after k other songs.

---

Core intuition (must remember)

At any position, two things matter:
• how many songs placed so far
• how many unique songs already used

Each step:
• either add a new song
• or replay an old song (if allowed)

This naturally leads to DP / recursion.

---

Key trick (why multiplication)

If:
• you have X choices now
• and each choice leads to Y valid futures

Total ways = X × Y

This is why we multiply choices with recursive calls.

---

Recursive state

dfs(pos, used)

pos  → current playlist length
used → number of distinct songs used so far

Return: number of valid playlists from this state.

---

Base case

If pos == goal
• return 1 if used == n
• else return 0

---

Transitions

1. Add a NEW song
   Allowed if used < n
   Choices = n − used
   Next state → (pos + 1, used + 1)

2. Replay an OLD song
   Allowed if used > k
   Choices = used − k
   Next state → (pos + 1, used)

---

Time & Space

States = goal × n
Time = O(goal × n)
Space = O(goal × n) for memo

*/

// Memoized recursive Java code (clean, interview-ready)

class Solution {
    static final int MOD = 1_000_000_007;
    Long[][] memo;
    int n, goal, k;

    public int numMusicPlaylists(int n, int goal, int k) {
        this.n = n; this.goal = goal; this.k = k;
        memo = new Long[goal + 1][n + 1];
        return (int) dfs(0, 0);
    }

    long dfs(int pos, int used) {
        if (pos == goal) return used == n ? 1 : 0;
        if (memo[pos][used] != null) return memo[pos][used];

        long ways = 0;
        if (used < n)
            ways += (n - used) * dfs(pos + 1, used + 1);
        if (used > k)
            ways += (used - k) * dfs(pos + 1, used);

        return memo[pos][used] = ways % MOD;
    }
}