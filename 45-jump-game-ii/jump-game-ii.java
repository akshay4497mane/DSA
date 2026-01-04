/*
Q: We must reach last index with minimum jumps. 
This becomes: at each step, choose the jump that extends our reachable window the farthest.

/*
🎯 Interview Presentation Order
Recursion → correctness, but exponential
DP + Memo → avoids repeats, still O(n·k)
Greedy → optimal, clean, O(n)

Complexity Summary
Approach  Time   Space 
Recursion O(kⁿ)  O(n)
DP + Memo O(n·k) O(n)
Greedy    O(n)   O(1)

Approach 1 : Brute Force (Recursion)
Try all possible jumps and take min.
Explores k-ary tree.
Time: O(kⁿ), Space: O(n)
*/

class Solution1 {
    int dfs(int i, int[] a) {
        if (i >= a.length-1) return 0; // no more jumps needed
        int min = 10000; // large value
        for (int j = 1; j <= a[i]; j++) // try all jump options
            min = Math.min(min, 1 + dfs(i+j, a));
        return min;
    }
    public int jump(int[] nums) {
        return dfs(0, nums);
    }
}

/*
2) DP + Memo (Top-Down)
Store memo[i] = min jumps from i to end.
Each state solved once.
Time: O(n·k), Space: O(n)
*/
class Solution2 {
    Integer[] memo;
    int dfs(int i, int[] a) {
        if (i >= a.length-1) return 0;
        if (memo[i] != null) return memo[i]; // reuse
        int min = 10000;
        for (int j = 1; j <= a[i]; j++)
            min = Math.min(min, 1 + dfs(i+j, a));
        return memo[i] = min;
    }
    public int jump(int[] nums) {
        memo = new Integer[nums.length];
        return dfs(0, nums);
    }
}

/*
3) Greedy BFS-Style (Optimized)
Track current jump window [l..r].
Within this, compute next farthest reach.
When i crosses r, we must jump.
Time: O(n), Space: O(1)
*/

class Solution {
    public int jump(int[] a) {
        int jumps = 0, l = 0, r = 0, reach = 0;
        while (r < a.length-1) { // until last covered
            for (int i = l; i <= r; i++) // check current window
                reach = Math.max(reach, i + a[i]); // farthest next
            jumps++; // take a jump
            l = r + 1; // next window start
            r = reach; // next window end
        }
        return jumps;
    }
}


//Approach 1 Sudipta
class Solution4 {
    public int jump(int[] nums) {
        int res=0, end=0, farthest=0;
        for(int i=0; i < nums.length-1; i++){
            farthest = Math.max( farthest, (i+nums[i]) );
            if( farthest >= nums.length-1 ){
                return ++res;
            }
            if(i==end){ //visited all item in current level
                res++;  // increment level
                end = farthest;  //queue size for next level
            }
        }
        return res;
    }
}

//Approach 3 Neetcode solution video
class Solution5 {
 public int jump(int[] nums) {
        int l=0,r=0; //window for DFS search after every jump
        int result=0;
        while( r < nums.length-1){
            int longestJump = 0;
            for(int i=l ; i<r+1 ; i++){
                longestJump = Math.max(longestJump, i + nums[i]);
            }
            l=r+1;
            r=longestJump;
            result++;
        }
        return result;
    }
}