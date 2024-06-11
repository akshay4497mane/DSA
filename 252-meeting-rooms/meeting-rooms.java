
class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        //Arrays.stream(intervals).forEach(interval -> System.out.println(Arrays.toString(interval)));
        for(int i=0; i<intervals.length-1; i++){
            if(intervals[i][1] > intervals[i+1][0])
                return false;
        }
        return true;
    }

    public boolean canAttendMeetings_Old(int[][] intervals) {  
        Arrays.sort(intervals,new Comparator<int[] >(){
          public int compare(int[] a, int[] b){
              return a[0]-b[0];
          }
        }); 
        int[] prev = intervals[0];
        for(int i=1;i<intervals.length;i++){
            int[] curr= intervals[i];
            if(prev[1]>curr[0])
            return false;
            prev=curr;
        }
        return true;
    }
}