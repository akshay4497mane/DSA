class Solution {
    public int numberOfSubstrings_1(String s) {
        String t= "abc";
        int[] need = new int[3];
        int missing = t.length();  // how many required chars still missing
        int ans=0;

        // initialize: mark every required char as "needed" (negative count)
        for (char c : t.toCharArray())
            need[c-'a']--;   // makes them negative => window needs these chars

        int L = 0, start = 0, minLen = Integer.MAX_VALUE;

        for (int R = 0; R < s.length(); R++) {
            char c = s.charAt(R); // ADD current char to window
            // If need[c] < 0, then this char was needed, so missing--.
            if (need[c-'a'] < 0) 
                missing--;
            need[c-'a']++;  // window now has one more 'c'

            while (missing == 0) { // If no chars are missing, try to shrink the left boundary
              ans=ans+s.length()-R;
                char left = s.charAt(L); // REMOVE left char from window
                need[left-'a']--; // we lose this char
                if (need[left-'a'] < 0)
                    missing++;  // window became insufficient again

                L++;  // shrink window
            }
        }

        return ans ;
    }

    public int numberOfSubstrings(String s) {        
        /* Array to store the last seen
        index of characters 'a', 'b', 'c'*/
        int[] lastSeen = {-1, -1, -1};
        
        int count = 0;
        
        // Iterate through each character in string s
        for (int i = 0; i < s.length(); ++i) {
            // Update lastSeen index 
            lastSeen[s.charAt(i) - 'a'] = i;
            
            /* Check if all characters 'a',
            'b', 'c' have been seen*/
            if (lastSeen[0] != -1 && lastSeen[1] != -1 && lastSeen[2] != -1) {
                
                /* Count valid substrings
                ending at current index*/
                count += 1 + Math.min(Math.min(lastSeen[0], lastSeen[1]), lastSeen[2]);
            }
        }
        
        // Return the total count of substrings
        return count;
    }
}