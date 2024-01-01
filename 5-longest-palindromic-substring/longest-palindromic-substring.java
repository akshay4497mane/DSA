class Solution {
    //Approach 1 : Brute Force, Generate all substrings, check all palindrome or not | Time : O(N^3) = N^2 * N
    public String longestPalindrome(String s) {
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
    //public String longestPalindrome(String s) {
        
    //}


}