class Solution {
    public long taskSchedulerII(int[] tasks, int space) {
        Map<Integer, Long> map = new HashMap<>();
        long time=0; 
        for(int t : tasks){
            if(map.containsKey(t)){
                Long val = map.get(t);
                Long allowed = val + space + 1;
                if (time < allowed) 
                    time = allowed;   
            }
            map.put(t, time);    
            time++;
        }
        return time;
    }
}
/*

Approach 1:
    public long taskSchedulerII(int[] tasks, int space) {
        Map<Integer, Long> last = new HashMap<>();;
        long res = 0;
        for (int a : tasks)
            if (last.containsKey(a))
                last.put(a, res = Math.max(res, last.get(a) + space) + 1);
            else
                last.put(a, ++res);
        return res;
    }

Approach 2 :

m[a] is the next avaible day for task type of a
Time O(n)
Space O(n)

public long taskSchedulerII(int[] tasks, int space) {
    Map<Integer, Long> next = new HashMap<>();;
    long res = 0;
    for (int a : tasks) {
        res = Math.max(next.getOrDefault(a, 0L), res + 1);
        next.put(a, res + space + 1);
    }
    return res;
}

*/