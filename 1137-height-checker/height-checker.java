class Solution {
    //Time: O(n log n + N)
    public int heightChecker(int[] heights) {
        int[] expected = Arrays.stream(heights).sorted().toArray();
        int ans = 0;
        for( int i=0; i<heights.length ; i++){
            if(heights[i] != expected[i])
                ans++;
        }
        return ans;
    }
}