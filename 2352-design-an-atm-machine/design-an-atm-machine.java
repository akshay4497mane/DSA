class ATM {
/* Sequence: 
$20, $50, $100, $200, and $500.
0    0     1     2          1
*/
    long[] bank;
    long[] denom = {20, 50, 100, 200, 500};
    public ATM() {
        bank = new long[5];
    }
    
    public void deposit(int[] banknotesCount) {
        for(int i=0; i<5; i++){
            bank[i] += banknotesCount[i];
        }
    }
    
    public int[] withdraw(int amount) {
        long[] ans = new long[5];
        int i = 4;
        while(amount>0 && i>=0){
            ans[i] = Math.min ( amount/denom[i], bank[i] );
            amount = (int) (amount - ans[i] * denom[i]);
            i--;
        }
        if(amount==0){
            for(int j=0; j<5; j++){
                bank[j] -= ans[j];
            }
            return Arrays.stream(ans).mapToInt(k -> (int)k).toArray();
        }else{
            return new int[] {-1};
        }
    }
}

/**
 * Your ATM object will be instantiated and called as such:
 * ATM obj = new ATM();
 * obj.deposit(banknotesCount);
 * int[] param_2 = obj.withdraw(amount);
 */