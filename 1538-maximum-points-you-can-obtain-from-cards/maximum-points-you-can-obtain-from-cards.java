class Solution {
    public int maxScore(int[] points, int k) {
        int lsum=0, rsum=0, maxSum=0, N=points.length;
        for(int i=0; i<k; i++) 
            lsum = lsum + points[i];
        maxSum = lsum;
        for(int lindex=k-1, rindex=N-1; lindex>=0; lindex--, rindex--){
            lsum = lsum - points[lindex];
            rsum = rsum + points[rindex];
            maxSum = Math.max(maxSum, lsum+rsum);
        }
        return maxSum;
    }
}

















/*
class Solution {
    public int maxScore(int[] cardPoints, int k) {
        [1,2,3,5,5,5,5, 4,5,6,1], k = 3
    1,2,3....5,6,1
    [x,y,z] => currSum
    0-k-1
    012 =>
    12(n-k)
    2(n-k)(n-k-1)
    
[1,2,3,4,5,6,1], k = 3
n=7, k=3
window = n-k
window(1234) => 5+6+1
window(2345) => 1+6+1
window(3456) => 1+2 + 1
window(4561) => 1+2+3
    }

//Sliding Window Solution by Gandhi better than Leetcode official
//Time( O(N) ) | Space: O(1)
public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int left = 0, right = n - k;
        int sum = 0;
        while (right < n) {
            sum += cardPoints[right];
            right++;
        }
        int maxSum = sum;
        right = n - k;
        while (right < n) {
            sum += cardPoints[left] - cardPoints[right]; 
            //Sliding winow here, 
            left++;
            right++;
            if( sum > maxSum ) maxSum=sum;
            // maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
}
*/