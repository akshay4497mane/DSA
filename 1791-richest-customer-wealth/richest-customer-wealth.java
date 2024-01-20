class Solution {
    public int maximumWealth(int[][] A) {
        int maxSumAns = 0; int rowSum=0;
        for(int i=0;i<A.length; i++){
            rowSum = 0;
            for(int j=0;j<A[0].length; j++){
                rowSum += A[i][j];
            }
            maxSumAns = Math.max(maxSumAns, rowSum);
        }
        return maxSumAns;
    }
}