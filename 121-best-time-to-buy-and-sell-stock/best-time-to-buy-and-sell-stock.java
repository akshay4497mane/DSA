class Solution {
    //Brute Force | For each buy day, Find largest possible selling price | 
    //Time : O(N^2)
    public int maxProfit_1(int prices[]) {
        int maxprofit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                int profit = prices[j] - prices[i];
                if (profit > maxprofit) maxprofit = profit;
            }
        }
        return maxprofit;
    }
    //Time O(N) | single pass | keep track of Minimum BUY price so far | try all values greater than that continuously | when you find lesser value update min so far value.
    public int maxProfit(int[] prices) {     
        int maxProfit =0, minBuyPriceSoFar=prices[0];
        for(int i=1; i<prices.length; i++){
            if( prices[i] > minBuyPriceSoFar ){
                maxProfit = Math.max(maxProfit, prices[i]-minBuyPriceSoFar);
            }else{
                minBuyPriceSoFar = prices[i];
            }
        }
        return maxProfit;
    }
}