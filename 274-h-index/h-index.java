class Solution {
    /* Approach 1 (N log N) : Sort ascending order + Search for index 
        -> we can visualize H-index as largest square in a plot of graph citations on y axis vs no of paper on x axis 
        -> answer is : find index such that citations[i]  < i
        Arrays.sort(arr, Collections.reverseOrder());
    */
    public int hIndex_approach1(int[] citations) {
        Integer[] newArray = new Integer[citations.length];
        int i = 0;
        for (int value : citations) {
            newArray[i++] = Integer.valueOf(value);
        }

        int N = newArray.length;
        Arrays.sort( newArray, Collections.reverseOrder() );
        for( i=0 ; i< N; i++ ){
            if( newArray[i] <= i ) break;
        }
        return i;
/* //Sorting Ascending Order :
        int i =1, N = citations.length;
        Arrays.sort( citations );
        for( i=1 ; i<= N; i++ ){
            if( citations[N-i] < i ) break;
        }
        return i-1;
*/
    }

/*
Approach 2 ( O(N) ) : Sort with counting sort in ascending order + Search for index 
    -> Replace all values > N as N [ not relevant for calculating h-index]
    -> Now since all array values are between 0-N we can use counting sort
The approach uses bucket sorting to calculate the h-index. For each citation in the array, the method increments the corresponding bucket, with any citation greater than or equal to *n* going into the last bucket. Then, it iterates backward through the buckets, summing the counts until the total is greater than or equal to the bucket index. This index is returned as the h-index.
    */
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] buckets = new int[n+1];
        for( int c : citations ){
            if(c >= n) 
                buckets[n]++;
            else 
                buckets[c]++;
        }
        int count = 0;
        for(int i=n; i>=0 ; i-- ){
            count += buckets[i];
            if( count >= i) 
                return i;
        }
        return 0;
    }
}