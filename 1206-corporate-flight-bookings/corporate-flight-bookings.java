class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] diff = new int[n+2], ans = new int[n];
        for(int[] booking : bookings){
            int L = booking[0], R = booking[1], val = booking[2];
            diff[L] += val;
            diff[R+1] += -1 * val;
        }
        //Arrays.stream(diff).forEach(x -> System.out.print(" " + x));
        int currSum=0;
        for( int i=1; i<=n; i++ ){
            currSum += diff[i];
            ans[i-1] = currSum;
        }
        return ans;
    }
}
/*
diff[N+1]     
[0,+10,+45,-10,-20, 0,-25]

+10     -10
    +20       -20
    +25                -25
1    2    3    4   5
Q: 1,2,3


for i=0-5
currSum: +10/55/

10  55 45  25 25 


q[1+1]
*/