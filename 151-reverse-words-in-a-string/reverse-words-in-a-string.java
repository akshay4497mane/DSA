class Solution {
    public String reverseWords(String input) {
        if(input == null) return null;
        char[] s = input.toCharArray();
        int n = s.length;
        reverse(s, 0, n-1);//Step 1 : reverse whole String
        reverseEachWord(s);//step 2 : reverse each word
        return cleanSpaces(s);//step 3 : clean Spaces
    }
    void reverse(char[] line, int left, int right){
        while(left<right){
            char temp = line[left];
            line[left++] = line[right];
            line[right--] = temp;
        }
    }
    void reverseEachWord(char[] s){
        int n = s.length, i=0, j=0;
        while( i< n ){
            while(i<n && s[i] ==' ') i++;
            j = i;
            while(j<n && s[j] != ' ')j++;
            reverse(s, i, j-1);
            i=j;
        }
    }
    String cleanSpaces(char[] s){
        int i=0, j=0, n = s.length;
        while(j<n){
            while( j < n && s[j] == ' ') j++;
            if(j==n)
                return new String(s).substring(0,i-1);
            while( j < n && s[j] != ' ') s[i++] = s[j++];
            if( j < n ) s[i++]=' ';
        }
        return new String(s).substring(0,i);
    }
}