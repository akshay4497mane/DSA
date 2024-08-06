class Solution {
    public int minimumPushes(String word) {
/* APPROACH : 
        //calculate frequency of each character
        //a -8, b-5, c-3, ...
        //sort based on frequency
        //Available Keys : 23456789
            //calculation
                First 8 chars: 1 * freq
                next 8 chars :  2 * freq
                next 8 chars : 3 * freq
*/
        int[] freq = new int[26];
        for( char c : word.toCharArray() ){
            freq[ c-'a' ]++;
        }
        Integer[] freq1 = new Integer[26];
        for(int i=0;i<26;i++)
            freq1[i] = freq[i];
        Arrays.sort(freq1, Collections.reverseOrder());
        int ans = 0;
        for(int i=0; i<26; i++){
            if(freq1[i] == 0) break;
            ans += (i/8 + 1) * freq1[i];
        }
        return ans;
    }
}