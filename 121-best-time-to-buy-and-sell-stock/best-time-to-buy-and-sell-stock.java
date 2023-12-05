class Solution {
    public int maxProfit(int[] prices) {
        /* Approach1: Brute Force Approach |try all pairs i,j | O(N^2)
        for buy 0:N-1
            for sell buy:N-1
                find max profit
        Approach2: O(N) | maintain buyi = 0, selli = -1, maxProfit = 0
        */
        int buyi=0, buyVal=prices[0];
        int selli, sellVal, currProfit, maxProfit =0;

        for(int i=1;i<prices.length ;i++){
            if( prices[i] < prices[buyi] )
                buyi = i;
            else{//sell
                currProfit = prices[i]-prices[buyi];
                if( currProfit > maxProfit){
                    maxProfit = currProfit;
                    selli = i;
                }
            }
        }
        return maxProfit;
    }
}