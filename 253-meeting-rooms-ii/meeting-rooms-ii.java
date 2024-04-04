class Solution {
    public int minMeetingRooms(int[][] intervals) {
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
}