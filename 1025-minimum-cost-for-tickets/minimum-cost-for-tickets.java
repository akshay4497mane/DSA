/*
Approach :
1. recursion/DFS
2. Memoization
3. DP Tabulation ( DP Bottom Up)
Time complexity: O(K) | Space complexity: O(K).
K is the last day we need to travel, the last value in the array days.
*/
class Solution {
    int[] DP, days, cost;
    int firstDay, lastDay;
    Set<Integer> travelSet = new HashSet<>();
    public int mincostTickets_Memoization(int[] d,int[] c){ 
        days = d; cost = c;
        firstDay = days[0]; lastDay = days[days.length-1];
        DP = new int[lastDay+1]; Arrays.fill(DP, -1);
        for(int day : days) travelSet.add(day);
        return dfs(firstDay);
    }
    int dfs(int currDay){ //Recursion + Memoization / Top Down DP
        if(currDay > lastDay) return 0;   //Dont make it >= OR ==
        if(DP[currDay] != -1) return DP[currDay]; //This may give ArrayIndexOut of Bound
        if(travelSet.contains(currDay)){ //if we can travel
            int cost1 = cost[0] + dfs(currDay + 1);
            int cost7 = cost[1] + dfs(currDay + 7);
            int cost30 = cost[2] + dfs(currDay + 30);
            return DP[currDay]=Math.min(cost1, Math.min(cost7, cost30));
        }else{ //No need to travel
            return DP[currDay]=dfs(currDay + 1);
        }
    }
/*
int[] DP, days, cost;
int firstDay, lastDay;
Set<Integer> travelSet = new HashSet<>();
DP Approach : Right --> Left
*/
public int mincostTickets(int[] d,int[] c){
    days=d; cost=c;
    firstDay=days[0]; 
    lastDay=days[days.length-1];

    DP=new int[lastDay+31]; // +30 safe buffer
    for(int day:days) travelSet.add(day);

    for(int currDay=lastDay; currDay>=firstDay; currDay--){
        if(travelSet.contains(currDay)){
            int cost1=cost[0]+DP[currDay+1];
            int cost7=cost[1]+DP[currDay+7];
            int cost30=cost[2]+DP[currDay+30];
            DP[currDay]=Math.min(cost1,Math.min(cost7,cost30));
        }else{
            DP[currDay]=DP[currDay+1];
        }
    }
    return DP[firstDay];
}
    
    
    
/* Bottom up DP : 
dp[day] = Min(dp[day - 1] + costs[0], dp[day - 7] + costs[1], dp[day - 30] + costs[2];
DP Approach : Left -> Right
*/
    public int mincostTickets_LeftToRight(int[] travelDays, int[] ticketCosts) {
        int finalTravelDay = travelDays[travelDays.length - 1];
        int[] minCostTillDay = new int[finalTravelDay + 1];
        int travelIndex = 0;
        for (int currentDay = 1; currentDay <= finalTravelDay; currentDay++) {
            if (currentDay < travelDays[travelIndex]) {
                minCostTillDay[currentDay] = minCostTillDay[currentDay - 1];
            } 
            else {
                travelIndex++; // move to next required travel day
                int costWith1DayPass  = minCostTillDay[currentDay - 1] + ticketCosts[0];
                int costWith7DayPass  = minCostTillDay[Math.max(0, currentDay - 7)]  + ticketCosts[1];
                int costWith30DayPass = minCostTillDay[Math.max(0, currentDay - 30)] + ticketCosts[2];

                minCostTillDay[currentDay] =Math.min(costWith1DayPass, Math.min(costWith7DayPass, costWith30DayPass));
            }
        }
        return minCostTillDay[finalTravelDay];
    }
}