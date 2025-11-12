
class Solution {
    public int[][] merge(int[][] intervals) {
          Arrays.sort(intervals, (a , b) -> {
            return a[0] - b[0];
        });
        
        ArrayList<int[]> list = new ArrayList<>();
        for(int[] interval: intervals){
            if(list.size() == 0){
                list.add(interval);
            } else {
                int[] last = list.get(list.size() - 1);
                if(interval[0] > last[1]){
                    list.add(interval);
                } else {
                    last[1] = Math.max(last[1], interval[1]);
                }
            }
        }
        
        return list.toArray(new int[list.size()][]);
    }
}

class Solution_2 {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            // if the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            }
            // otherwise, there is overlap, so we merge the current and previous
            // intervals.
            else {
                merged.getLast()[1] = Math.max(
                    merged.getLast()[1],
                    interval[1]
                );
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}