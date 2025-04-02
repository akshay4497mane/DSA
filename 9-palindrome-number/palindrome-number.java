class Solution {
    public boolean isPalindrome(int x) {
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