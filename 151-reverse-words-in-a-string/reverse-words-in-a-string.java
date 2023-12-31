class Solution {
    public String reverseWords(String input) {
        if(input == null) return null;
        char[] s = input.toCharArray();
        int n = s.length;
        //Step 1 : reverse whole String
        reverse(s, 0, n-1);
        System.out.println("After Reverse : [" + new String(s) + "]");
        //step 2 : reverse each word
        reverseEachWord(s);
        System.out.println("After ReverseWords : [" + new String(s) + "]");

        //step 3 : clean Spaces
        String ans = cleanSpaces(s);
        System.out.println("After cleanSpaces() : [" + ans + "]");
        return ans;
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
        System.out.println("START: " + new String(s));
        while(j<n){
            while( j < n && s[j] == ' ') j++;
            if(j==n)
                return new String(s).substring(0,i-1);
            while( j < n && s[j] != ' ') s[i++] = s[j++];
            if( j < n ) s[i++]=' ';
            System.out.println("i: " + i + " j: " + j + "n:" + n);
        }
        System.out.println(new String(s));
        if(j==n)
            return new String(s).substring(0,i);
        else        
            return new String(s).substring(0,i-1);
    }
}