class Solution {
    /* Approach 1 (N log N) : Sort ascending order + Search for index 
        -> we can visualize H-index as largest square in a plot of graph citations on y axis vs no of paper on x axis 
        -> answer is : find index such that citations[i]  < i
        Arrays.sort(arr, Collections.reverseOrder());
    */
    public int hIndex(int[] citations) {
        int i =1, N = citations.length;
        Arrays.sort( citations );
        for( i=1 ; i<= N; i++ ){
            if( citations[N-i] < i ) break;
        }
        return i-1;
    }
}