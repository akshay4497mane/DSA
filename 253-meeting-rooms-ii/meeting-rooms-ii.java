class Solution {
/*
Approach 1 : O(N log N), Space : O(N)
Sort(start time), Use Priority Queue(Rooms) to keep track, 
For each interval, Compare start time with PQ MIN END time, if greater remove room and allocate same otherwise allocate new room.
*/
    public int minMeetingRooms(int[][] meetingsArr) {
        Arrays.sort(meetingsArr, (a, b) -> a[0]-b[0] );
        //Arrays.stream(meetingsArr).forEach( e -> System.out.println(Arrays.toString(e)));
        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        for( int[] meet: meetingsArr ){
            int currStart = meet[0], currEnd = meet[1];
            if( !rooms.isEmpty() && currStart >= rooms.peek() )
                rooms.remove();
            rooms.add( currEnd );
        }
        return rooms.size();
    }
/*
APPROACH 2 : 
separate start, end arrays; sort separately;
For loop(startIndex, endIndex), 
*/
    public int minMeetingRooms_2(int[][] meetingsArr) {
        Integer[] start = new Integer[meetingsArr.length];
        Integer[] end = new Integer[meetingsArr.length];
        for(int i=0; i<meetingsArr.length ; i++){
            start[i] = meetingsArr[i][0]; end[i] = meetingsArr[i][1];
        }
        Arrays.sort(start); Arrays.sort(end);
        int roomsCount = 0;
        for(int si = 0, ei = 0; si < start.length; ){
            if( start[si] >= end[ei] ){
            // If there is a meeting that has ended by the time the meeting at `si` starts
                roomsCount--;
                ei++;
            }
        // We do this irrespective of whether a room frees up or not.
        // If a room got free, then this used_rooms += 1 wouldn't have any effect. used_rooms would
        // remain the same in that case. If no room was free, then this would increase used_rooms
            roomsCount++;
            si++;
        }
        return roomsCount;
        //Arrays.stream(start).forEach(System.out::print);
        //Arrays.stream(end).forEach(System.out::print);
    }
}