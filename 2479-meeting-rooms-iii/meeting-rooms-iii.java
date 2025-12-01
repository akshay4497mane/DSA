class Solution {
//Time complexity: O(M⋅logM+M⋅logN)O(M\cdot logM + M\cdot logN)O(M⋅logM+M⋅logN). 

/*
Let NNN be the number of rooms.
Let MMM be the number of meetings.
Time complexity: O(M⋅logM+M⋅N)
*/
    public int mostBooked(int n, int[][] meetings) {
        long[] roomFreeTime = new long[n];//At what time each ROOM will be free?
        int[] meetCount = new int[n]; //COUNT of meetings in every room
        Arrays.sort(meetings, (a,b) -> a[0] - b[0] );//SORT based on START time

        for( int[] meet : meetings ){
            int start = meet[0], end = meet[1];
            boolean foundUnusedRoomFlag = false;
            long minRoomFreeTime = Long.MAX_VALUE; int minRoomFreeIndex = 0; //if all rooms are busy, Find EARLIEST Free Room
            for( int i=0; i<n ;i++ ){//for each rooms
                if( roomFreeTime[i] <= start ){
                    foundUnusedRoomFlag = true;
                    meetCount[i]++;
                    roomFreeTime[i] = end;
                    break;
                }else{
                    if( minRoomFreeTime > roomFreeTime[i] ){ //Find MINIMUM / earliest free room
                        minRoomFreeTime = roomFreeTime[i];
                        minRoomFreeIndex = i;
                    }
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

    public int mostBooked_2(int n, int[][] meetings) {
        long[] freeAt = new long[n];   // when each room becomes free
        int[] used = new int[n];       // meeting count per room
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        for (int[] m : meetings) {
            int start = m[0], end = m[1];

            int freeRoom = -1;
            long earliestTime = Long.MAX_VALUE;
            int earliestRoom = 0;

            for (int i = 0; i < n; i++) {
                if (freeAt[i] <= start && freeRoom == -1) {
                    freeRoom = i;     // first available room
                }

                if (freeAt[i] < earliestTime) {
                    earliestTime = freeAt[i];
                    earliestRoom = i;
                }
            }

            if (freeRoom != -1) {
                freeAt[freeRoom] = end;
                used[freeRoom]++;
            } else {
                freeAt[earliestRoom] = earliestTime + (end - start);
                used[earliestRoom]++;
            }
        }

        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (used[i] > used[ans]) ans = i;
        }
        return ans;
    }

}