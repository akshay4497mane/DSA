class Solution {
    /* Approach 1: Find low digits using %,/ and Convert num to string, compare with 2 pointers
    Time: O(N), Space : O(N)
    Approach 2 : Find low digits by %/, convert num into ReverseNumber, But it may lead to overflow
    Approach 3: Find low dig and convert only half of the integer
     */
    public boolean isPalindrome(int x) {
        if(x<0) return false;//handle -ve case
        if(x%10 == 0 && x!=0) return false; //handle 132000 case | reverse 231
        int reverseX = 0;
        while( reverseX < x ){
            reverseX = reverseX * 10 + (x%10);
            x = x/10;
        }
        if( x==reverseX) return true;//123321 => 123 == 321
        if( x==reverseX/10) return true; // 12321 => 12 == 123/10
        return false;
    }
    public boolean isPalindrome_approach1(int x) {
        if(x<0) return false;
        StringBuilder sb = new StringBuilder();
        while(x > 0){
            System.out.println(x%10);
            sb.append(x % 10);
            x= x/10;
        }
    System.out.println(sb.toString());
    return isPali( sb.toString() );
    }
    //null, a, ab, aba, abc
    boolean isPali(String s){
        int left=0, right=s.length()-1; 
        while( left<=right && s.charAt(left) == s.charAt(right) ){
            left++; right--;
        }
        if(left<=right) 
            return false;
        else
            return true;
    }
}