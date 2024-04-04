class Solution {
    public int mostBooked(int n, int[][] meetings) {
        long[] roomFreeTime = new long[n];//At what time each ROOM will be free?
        int[] meetCount = new int[n]; //COUNT of meetings in every room

        Arrays.sort(meetings, (a,b) -> a[0] - b[0] );//SORT based on START time

        for( int[] meet : meetings ){
            int start = meet[0], end = meet[1];
            boolean foundUnusedRoomFlag = false;
            //if all rooms are busy, Find EARLIEST Free Room
            long minRoomFreeTime = Long.MAX_VALUE; int minRoomFreeIndex = 0; 
            for( int i=0; i<n ;i++ ){
                if( roomFreeTime[i] <= start ){
                    foundUnusedRoomFlag = true;
                    meetCount[i]++;
                    roomFreeTime[i] = end;
                    break;
                }
                if( minRoomFreeTime > roomFreeTime[i] ){
                    minRoomFreeTime = roomFreeTime[i];
                    minRoomFreeIndex = i;
                }
            }
            if( !foundUnusedRoomFlag ){ //if NO room Found, Calculate wait time for EARLIEST/MINIMuM room
                roomFreeTime[minRoomFreeIndex] = minRoomFreeTime + end - start;
                meetCount[minRoomFreeIndex] ++;
            }
        }
        int maxMeetCount = 0, maxMeetCountRoomIndex = 0;
        for(int i=0; i<n ; i++){
            if( meetCount[i] > maxMeetCount ){
                maxMeetCount = meetCount[i];
                maxMeetCountRoomIndex = i;
            }
        }
        return maxMeetCountRoomIndex;
    }
}