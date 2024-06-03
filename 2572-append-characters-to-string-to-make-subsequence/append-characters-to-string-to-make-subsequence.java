class Solution {
//Approach : compare and search for characters in secod string T into first string S
//O(N) time, O(1) space
    public int appendCharacters(String s, String t) {
        int i =0 , j= 0, lenS = s.length(), lenT = t.length();
        while( i < lenS && j < lenT ){
            if(s.charAt(i)==t.charAt(j)){
                i++; j++;
            }else{
                i++;
            }
        }
        return lenT - j;
    }
}

/*
Test cases:
akshay , mane , 4
akshay, pooja , 5
akshay, ankita, 5,
akash, akshay, 

Edge Cases:
"" , "abcd" => 4
"abcd", "" => 0
"abcd", "abcd" =>0
 */