class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] lastSeen = new int[128];
        Arrays.fill(lastSeen, -1);
        int L =0, R=0, ans=0;
        for(;R<s.length(); R++){
            char ch = s.charAt(R);
            //check validity , if invalid...move left
            if(lastSeen[ch] >= L){
                L = lastSeen[ch]+1;
            }
            lastSeen[ch] = R;
            ans = Math.max(ans, R-L+1);
        }
        return ans;
    }
    //Time O(N), Space : O(128)






















/*
    public int lengthOfLongestSubstring(String s) {
        int[] charIndex = new int[128]; //26 for a-z OR A-Z | 128 for ASCII | 256 for extended ascii
        Arrays.fill(charIndex, -1);
        int left = 0, ans = 0;
        for(int right = 0; right<s.length() ; right++){
            char c = s.charAt(right);
            if(charIndex[c] != -1 && charIndex[c] >= left)
                left = charIndex[c] + 1;
            charIndex[c] = right;
            ans = Math.max(ans, right-left+1);
        }
        return ans;
    }
*/
}