class Solution {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int maxArea = Integer.MIN_VALUE;
        double currDiagSq, maxDiagSq = Double.MIN_VALUE;
        for(int[] rect : dimensions){
            currDiagSq = rect[0] * rect[0] + rect[1] * rect[1] ;
            if(currDiagSq > maxDiagSq ){
                maxDiagSq = currDiagSq;
                maxArea = rect[0] * rect[1];
            }else if( currDiagSq == maxDiagSq ){
                maxArea = Math.max(maxArea, rect[0] * rect[1]);
            }
            System.out.println("" + rect[0] + " " + rect[1] + " " + currDiagSq + " " + maxDiagSq + " " + maxArea);
        }
        return maxArea;
    }
}