class Solution {
/*
1st pass: No of shifts required can be precalculated in one array traversal.
2nd pass : compute shifting of characters 
Need to handle modulo 26 for overflows in both loops
*/
    public String shiftingLetters(String s, int[] shifts) {
        StringBuilder sb = new StringBuilder();
        for(int i = shifts.length-2; i>=0 ; i--){
            shifts[i] = (shifts[i+1] + shifts[i]) % 26;
        }
        //Arrays.stream(shifts).forEach(System.out::println);
        for(int i=0; i<s.length() ; i++ ){
            sb.append((char) ((( (s.charAt(i)-'a') + shifts[i]) % 26 ) + 'a') );
        }
        return sb.toString();
    }
}

class Solution2 {
    public String shiftingLetters(String s, int[] shifts) {
        char p[]=s.toCharArray();
        long n=0;
        for(int i=0;i<shifts.length;i++)
            n+=shifts[i];

        for(int i=0;i<p.length;i++){
            long ch=(p[i]-97+n)%26;
            p[i]=(char)(97+ch);
            n-=shifts[i];
        }
        return String.valueOf(p);
    }
}