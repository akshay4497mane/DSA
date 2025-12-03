/*
1. recursion/DFS
2. Memoization
3. DP Tabulation ( DP Bottom Up)
*/
class Solution {
    int[] DP; int[] days, cost;
    int firstDay, lastDay;
    Set<Integer> travelSet = new HashSet<>();
    public int mincostTickets(int[] d,int[] c){ 
        days = d; cost = c;
        firstDay = days[0];
        lastDay = days[days.length-1];
        DP = new int[lastDay+1];
        Arrays.fill(DP, -1);
        for(int day : days) travelSet.add(day);
        return dfs(1);
    }
    int dfs(int currDay){
        if(currDay > lastDay) return 0;   //Dont make it >= OR ==
        if(DP[currDay] != -1) return DP[currDay]; //This may give ArrayIndexOut of Bound
        if(travelSet.contains(currDay)){
            int cost1 = cost[0] + dfs(currDay + 1);
            int cost7 = cost[1] + dfs(currDay + 7);
            int cost30 = cost[2] + dfs(currDay + 30);
            return DP[currDay]=Math.min(cost1, Math.min(cost7, cost30));
        }else{
            return DP[currDay]=dfs(currDay + 1);
        }
    }
}