class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int maxDest = Integer.MIN_VALUE;
        for(int[] trip : trips) maxDest = Math.max(maxDest, trip[2]);
        int[] diff = new int[maxDest+2];
        for(int[] trip : trips){
            int numPassengers = trip[0];
            int from = trip[1];
            int to = trip[2];
            diff[from] += numPassengers;
            diff[to] += -1 * numPassengers;
        }
        int currSum = 0;
        for(int i=0; i<= maxDest; i++){
            currSum += diff[i];
            if(currSum > capacity) return false;
        }
        return true;
    }
}
/*

*/
/*
N= 1 2 3 4 5  6 7 8 9
   2         -2
       3          -3
CAR ->     CAR
Query :  pass, 1->5
----
N= 1 2 3 4 5  6 7 8 9
   2         -2
       3          -3
CAR ->     CAR
Query :  pass, 1->5
*/