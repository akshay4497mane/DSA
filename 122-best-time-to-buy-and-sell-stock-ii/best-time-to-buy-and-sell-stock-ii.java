class Solution {
    public int maxProfit(int[] prices) {
        int profit =0, buyPrice=prices[0];
        for(int i=1; i<prices.length; i++){
            if(prices[i]>buyPrice){
                profit += (prices[i]-buyPrice);
            }
            buyPrice = prices[i];
        }
        return profit;        
    }
}