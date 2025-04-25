class Solution {
    public int balancedStringSplit(String s) {
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
}