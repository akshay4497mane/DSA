class Solution {
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> charFreq = new HashMap<>();
        for(char c : tasks){
            charFreq.put(c, charFreq.getOrDefault(c,0)+1);
        }
        charFreq.forEach((a, b) -> System.out.println(a + ": " + b));
        PriorityQueue<Integer> pq = new PriorityQueue<>( (a,b) -> b-a );
        pq.addAll(charFreq.values());
        int timeAns = 0;
        while(!pq.isEmpty()){
            pq.forEach( (a) -> System.out.print(a + " "));
            System.out.println("TimeAns : " + timeAns);

            List<Integer> taskPartiallyDone = new ArrayList<>();
            for(int i=0 ; i<n+1 ; i++ ){//A task can repeat after N tasks
                if(!pq.isEmpty() ){
                    int curr = pq.remove();
                    if(curr!=1)
                        taskPartiallyDone.add( curr -1 );
                }else if( taskPartiallyDone.isEmpty() ){
                    return timeAns;
                }
                timeAns++;
            }
            for(int i=0; i < taskPartiallyDone.size(); i++){
                if( taskPartiallyDone.get(i) != 0)
                    pq.add(taskPartiallyDone.get(i));
            }
            taskPartiallyDone.clear();
        }
        return timeAns;
    }
}