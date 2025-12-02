class Solution {
/*
Approach 1 :
Find i,j such that i <j, Score is max
Score(i,j) = vali + valj + i -j = (vali + i)  + (valj - j)
for every j = 
Time O(N) | Space: O(N)
*/
    public int maxScoreSightseeingPair(int[] values) {
        int n = values.length;
        int[] maxLeftScore = new int[n];
        maxLeftScore[0] = values[0] + 0;
        int maxScore = 0;
        for (int j = 1; j < n; j++) {
            int currRightScore = values[j]-j;
            maxScore = Math.max(maxScore, maxLeftScore[j-1] + currRightScore);
            int currLeftScore = values[j] + j;
            maxLeftScore[j] = Math.max(maxLeftScore[j-1], currLeftScore);
        }
        return maxScore;
    }
}