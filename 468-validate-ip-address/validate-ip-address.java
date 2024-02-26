
class Solution {
/*APPROACH 1 :
step 1 : split IP : IP.split("\\.",-1);  
step 2 : check length 4: ipv4, 8: ipv6
step 3 : validate ipv4 :( length 1-3, cant start with 0 && length != 1, num betw 0-255 )
step 4 : validate ipv6 :( length 1-4, Integer.parseInt(num, 16) ) 
 */
    public String validIPAddress(String IP) {
        String[] v4 = IP.split("\\.",-1);
        String[] v6 = IP.split("\\:",-1);
        if( v4.length == 4 )
            return validateIPv4(v4);
        if( v6.length == 8 ) 
            return validateIPv6(v6);
         else
            return "Neither";
    }
    public String validateIPv4(String[] ipv4){
        for( String num : ipv4 ){
            if( num.length()<=0 || num.length() > 3) return "Neither";
            if( num.charAt(0)=='0' && num.length()!=1 ) return "Neither";
            try{
                int n = Integer.valueOf(num);//Integer.parseInt(num);
                if( n<0 || n>255) return "Neither";
            }catch(NumberFormatException e){
                return "Neither";
            }
        }
        return "IPv4";
    }
    public String validateIPv6(String[] ipv6){
        for( String num : ipv6 ){
            if( num.length()<=0 || num.length() > 4) return "Neither";
            try{
                int n = Integer.parseInt(num, 16);
            }catch(NumberFormatException e){
                return "Neither";
            }
        }
        return "IPv6";
    }
}