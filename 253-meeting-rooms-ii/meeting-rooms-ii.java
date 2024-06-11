class Solution {

/*
Approach 1 : O(N log N), Space : O(N)
Sort(start time), Use Priority Queue(Rooms) to keep track, 
For each interval, Compare start time with PQ MIN END time, if greater remove room and allocate same otherwise allocate new room.
*/
    public int minMeetingRooms_Approach_PQ(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0]-b[0] );
        //Arrays.stream(intervals).forEach( e -> System.out.println(Arrays.toString(e)));
        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        for( int i=0; i< intervals.length ; i++ ){
            if( !rooms.isEmpty() && intervals[i][0] >= rooms.peek() )
                rooms.remove();
            rooms.add( intervals[i][1] );
        }
        return rooms.size();
    }
/*
APPROACH 2 : 
separate start, end arrays; sort separately;
For loop(startIndex, endIndex), 

*/
    public int minMeetingRooms(int[][] intervals) {
        Integer[] start = new Integer[intervals.length];
        Integer[] end = new Integer[intervals.length];
        for(int i=0; i<intervals.length ; i++){
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int roomsCount = 0;
        for(int si = 0, ei = 0; si < start.length; ){
            if( start[si] >= end[ei] ){
                roomsCount--;
                ei++;
            }
            roomsCount++;
            si++;
        }
        return roomsCount;
        //Arrays.stream(start).forEach(System.out::print);
        //Arrays.stream(end).forEach(System.out::print);
    }
}