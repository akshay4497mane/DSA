/*
1) Brute Force (Recursion)
Try every possible jump from each index.
If any path reaches last index → return true.
Time: O(2ⁿ), Space: O(n)
*/
class Solution1 {
    boolean dfs(int i, int[] a) {
        if (i >= a.length-1) return true; // reached end
        for (int j = 1; j <= a[i]; j++) // try all jumps
            if (dfs(i+j, a)) return true;
        return false;
    }
    public boolean canJump(int[] nums) {
        return dfs(0, nums);
    }
}
/*
2) DP + Memo (Top-Down)
Avoid recomputing using memo.
Time: O(n²), Space: O(n)
*/
class Solution2 {
    Boolean[] memo;
    boolean dfs(int i, int[] a) {
        if (i >= a.length-1) return true;
        if (memo[i] != null) return memo[i]; // reuse result
        for (int j = 1; j <= a[i]; j++)
            if (dfs(i+j, a)) return memo[i] = true;
        return memo[i] = false;
    }
    public boolean canJump(int[] nums) {
        memo = new Boolean[nums.length];
        return dfs(0, nums);
    }
}

/*
3) Greedy (Optimized)
Maintain farthest reachable index.
If at any point i > reach, we got stuck → false.
Finally check if reach covers last index.
Time: O(n), Space: O(1)
*/
class Solution {
    public boolean canJump(int[] a) {
        int reach = 0;
        for (int i = 0; i < a.length; i++) {
            if (i > reach) return false; // cannot reach this index
            reach = Math.max(reach, i + a[i]); // update farthest reach
        }
        return true; // if loop completes, last is reachable
    }
}


/*
Jump Game 2 modified for jump game 1
Sliding window approach
*/
class Solution4 {
    public boolean canJump(int[] nums) {
       int jumps = 0, l = 0, r = 0;
       while (r < nums.length - 1) {
           int farthest = 0;
           for (int i = l; i <= r; i++) {
               farthest = Math.max(farthest, i + nums[i]);
           }
           l = r + 1;
           if(farthest <= r) 
                return false;
           else
                r = farthest;
           jumps++;
       }
       return true;
    }
}