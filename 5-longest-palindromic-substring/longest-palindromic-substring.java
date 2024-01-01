class Solution {
/*
    Approach 1 : Brute Force, Generate all substrings, check all palindrome or not | 
    Time : O(N^3) = N^2 * N
*/
    public String longestPalindromeBruteForce(String s) {
        int n = s.length(), maxLen = 1;
        String maxStr = s.substring(0,1);
        for(int i=0; i<n ; i++){
            for(int j=i+maxLen; j<=n;j++){
                if( j-i > maxLen && isPalindrome( s.substring(i,j) )){
                    maxLen = j-i;
                    maxStr = s.substring(i, j);
                }
            }
        } 
        return maxStr;  
    }
    boolean isPalindrome(String s){
        if(s==null) return true;
        int i=0, j=s.length() -1;
        while( i<j ){
            if(s.charAt(i++) != s.charAt(j--)) return false;
        }
        return true;
    }
    /*
    APPROACH 2 :
        for every i, 
        see if s[i] is centre of odd palindrome or
            if s[i]&s[i+1] is centre of even palindrome
        Time: O(N^2)
    */
    
    public String longestPalindrome(String s) {
        String maxStr = s.substring(0,1);
        for(int i=0; i<s.length()-1; i++){
            String odd = expandFromCentre(s,i,i);
            String even = expandFromCentre(s,i,i+1);
            if(odd.length() > maxStr.length())
                maxStr = odd;
            if(even.length() > maxStr.length())
                maxStr = even;            
        }
        return maxStr;        
    }
    String expandFromCentre(String s, int i, int j){
        while( i >= 0 && j < s.length() && s.charAt(i)==s.charAt(j) ){
            j++; i--;
        }
        return s.substring(i+1, j);
    }
}