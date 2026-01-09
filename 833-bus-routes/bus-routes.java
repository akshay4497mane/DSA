class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if(source == target) return 0;
        //bus0=[1,2,7], bus1[3,6,7]
        //step1: source is STOP -> find out all possible BUS i can take?
        int totalBusCount = routes.length; //0-N buses
        Map<Integer, List<Integer>> stopToBus = new HashMap<>(); //Stop is present in which all bus routes -> bus1, bus2, bus5
        for(int bus=0; bus<totalBusCount; bus++){
            for(int stop : routes[bus]){
                stopToBus.computeIfAbsent(stop, k -> new ArrayList<>()).add(bus);
            }
        }
        System.out.println(stopToBus);
        //{1=[0], 2=[0], 3=[1], 6=[1], 7=[0, 1]}
        //Step 2: start BFS from source -> Explore/do BFS on all BUS ROUTES available -> continue BFS untill you hit target
        //maintain 2 visited 1.BUS 2.Stops
        boolean[] visitedBuses = new boolean[totalBusCount]; //0-N buses
        Set<Integer> visitedStops = new HashSet<>();
        Queue<Integer> q = new ArrayDeque<>();
        int ansBuses = 1;
        //At level 0 -> you are at stop "SOURCE" -> you may be at multiple BUSES as starting point.-> explore all with BFS and ans=0
        for( int bus : stopToBus.computeIfAbsent(source, k -> new ArrayList<>()) ){
            q.add(bus);
            visitedBuses[bus] = true;
        }
        while( !q.isEmpty() ){
            int levelSize = q.size();
            for(int i=0; i<levelSize; i++){
                int currBus = q.poll();
                for( int stop : routes[currBus]){
                    if(stop == target) return ansBuses;
                    if(visitedStops.contains(stop)) continue;
                    visitedStops.add(stop);
                    //For each stop -> find which all BUS LINES we can take?
                    for( int nextBus : stopToBus.get(stop)){
                        if(!visitedBuses[nextBus]){
                            visitedBuses[nextBus] = true;
                            q.add(nextBus);
                        }
                    }
                }
            }
            ansBuses++;
        }
        return -1;
    }
}



/*  Printing a Map
    Map<Integer, List<Integer>> stopToBus = new HashMap<>();
    //Print the map directly
    System.out.println("Direct Print: " + stopToBus);

    //Print the map using an enhanced for-loop for more control (optional)
    System.out.println("\nUsing a for-loop:");
    for (Map.Entry<Integer, List<Integer>> entry : stopToBus.entrySet()) {
        System.out.println("Stop ID " + entry.getKey() + " is served by Buses: " + entry.getValue());
    }
*/