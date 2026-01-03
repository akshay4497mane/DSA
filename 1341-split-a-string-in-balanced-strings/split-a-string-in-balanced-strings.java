class Solution {
    public int balancedStringSplit_1(String s) {
        int i=0, countL=0, countR=0, n = s.length(), ans=0;
        while(i < n){
            if('L' == s.charAt(i)){
                countL++;
            }else if('R' == s.charAt(i)){
                countR++;
            }

            if(countL == countR){
                ans++;
                countL=0;
                countR=0;
            }
            i++;
        }
        return ans;
    }

    public int balancedStringSplit(String s){
        int bal=0,ans=0;
        for(int i=0;i<s.length();i++){
            bal += s.charAt(i)=='R'?1:-1;
            if(bal==0) ans++;
        }
        return ans;
    }
}