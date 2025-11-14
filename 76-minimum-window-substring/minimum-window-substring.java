class Solution {
    /*
    Sliding Window L-R
    jab ham right ko move kare tab hash[ch] if positive matlab dusari string me dala gaya tha => count++ => because current window me ans aa raha he
    
    when we find ch => frequency --
    when we remove ch from window frequency++

    Striver Video
    https://www.youtube.com/watch?v=WJaij9ffOIY

    */
    public String minWindow_1(String s, String t) {
        int L=0, R=0, startIndex=-1, count = 0, minLen=Integer.MAX_VALUE;
        int[] hash = new int[256];
        for(char ch : t.toCharArray())
            hash[ch]++;
        while( R<s.length() ){
            char c = s.charAt(R);
            if(hash[c] > 0) count++;
            hash[c]--;

            while(count==t.length()){
                if(R-L+1 < minLen){ 
                    minLen = Math.min(minLen, R-L+1);
                    startIndex = L;
                }

                hash[s.charAt(L)]++;
                if(hash[s.charAt(L)]>0)
                    count = count-1;
                L++;
            }
            R++;
        }
        if(startIndex==-1) return "";
        return s.substring(startIndex, startIndex+minLen);
    }


    public String minWindow(String s, String t) {
        int[] need = new int[256];
        int missing = t.length();

        // reverse meaning: we will decrement for T
        for (char c : t.toCharArray()) need[c]--;

        int L = 0, minLen = Integer.MAX_VALUE, start = 0;

        for (int R = 0; R < s.length(); R++) {
            char c = s.charAt(R);

            // add: ++
            if (need[c] < 0) missing--;
            need[c]++;

            // shrink
            while (missing == 0) {
                if (R - L + 1 < minLen) {
                    minLen = R - L + 1;
                    start = L;
                }
                char left = s.charAt(L);

                // remove: --
                need[left]--;
                if (need[left] < 0) missing++;
                L++;
            }
        }

        if (minLen == Integer.MAX_VALUE) return "";
        return s.substring(start, start + minLen);
    }
}