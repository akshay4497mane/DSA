class Solution {
/*
Approach 1 :
-> Only Prefix of shortest string can be a GCD.( longer string can never be answer )
-> So we try out all prefixes of shortest string. eg. //str1 = "ABABAB", str2 = "ABAB" | --> k = 4,3,2,1 => ABAB, ABA, AB, A
-> Since we need LONGEST, we traverse from RIGHT to LEFT(4,3,2,1).
-> use replace(base,"") to make both strings empty.
Time : O(min(m,n)⋅(m+n)).
Space complexity: O(min(m,n))
*/
    public String gcdOfStrings_1(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        for(int i=Math.min(len1,len2); i>=1; i-- ){
            if(isValid(s1,s2,i)){
                return s1.substring(0,i);
            }
        }
        return "";
    }
    private boolean isValid(String s1, String s2, int k){
        //str1 = "ABABAB", str2 = "ABAB" | k = 4,3,2,1
        int len1 = s1.length(), len2 = s2.length();
        if( len1%k !=0 || len2 % k !=0 ){
            return false;
        }else{ //only for k=2
            String base = s1.substring(0,k);
            return s1.replace(base, "").isEmpty() && s2.replace(base, "").isEmpty();
        }
    }
/*
Here’s the complete visualization in one compact table for input
str1 = "ABABAB", str2 = "ABAB"

i	base (prefix of str1)	len1 % i	len2 % i	str1.replace(base,"")	str2.replace(base,"")	valid?	reason
4	"ABAB"	2	0	—	—	false	len1 not divisible by 4
3	"ABA"	0	1	—	—	false	len2 not divisible by 3
2	"AB"	0	0	""	""	true	both strings = repeat of "AB"
1	"A"	—	—	(not checked)	(not checked)	—	loop stops once valid found
Final answer: "AB"
*/

/*
Approach 2 : 
1. If strings contains multiples of base, their concatenation must be consistent, regardless of the order (str1 + str2 = str2 + str1).
2. Answer should be of length GCD(str1.len, str2.len)
Time complexity: O(m+n)
Space complexity: O(m+n)
*/
    public String gcdOfStrings(String s1, String s2){
        if( !(s1+s2).equals(s2+s1) ){
            return "";
        }
        int gcdLen = gcd(s1.length(), s2.length());
        return s1.substring(0, gcdLen);
    }
    int gcd(int a, int b){
        //Euclid’s algorithm gcd(a, b) = gcd(b, a % b)
        if(b==0){
            return a;
        }else{
            return gcd(b, a%b);
        }
    }
}
