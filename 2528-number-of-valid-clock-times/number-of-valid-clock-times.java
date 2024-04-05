class Solution {
    public int countTime(String time) {
        int ans = 1;
        if(time.charAt(0)=='?' && time.charAt(1)=='?'){
            ans *= (10+10+4);
        }else if( time.charAt(0) == '?' ){
            //  ?_ : 
            //?4     only 0 , 1
            //?3     only 0,1,2
            ans *= ( time.charAt(1) - '0' >= 4 )? 2 : 3 ;
        }else if( time.charAt(1) == '?' ){
           //  _?      :   2_  can have only 0,1,2,3 |   OR | 10 possible     
            ans *= ( time.charAt(0) - '0' == 2 )? 4 : 10 ;
        }
        if( time.charAt(4) == '?' ) ans *= 10;
        if( time.charAt(3) == '?') ans *= 6;
        return ans;
    }
}