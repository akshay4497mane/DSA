class Solution {
    public int lengthOfLongestSubstring(String s) {
        int l=0,r=0, ans=0, N=s.length();
        Set<Character> set = new HashSet<>();
        while(r<N){
            char ch = s.charAt(r);
            while(l<r && set.contains(ch)){
                set.remove(s.charAt(l));
                l++;
            }
            set.add(ch);
            ans = Math.max(ans, r-l+1);
            r++;
        }
        return ans;
    }
}























class Solution1 {
/*
14Nov2025
Sliding Window, Untill window has unique characters
Time O(N), Space : O(128)
*/
public int lengthOfLongestSubstring(String s) {
    int[] lastSeen = new int[128];     // store last index of each character
    Arrays.fill(lastSeen, -1);         // -1 means "not seen yet"
    int L = 0;                         // window start
    int ans = 0;
    for (int R = 0; R < s.length(); R++) {
        char ch = s.charAt(R);

        // if this character was seen inside the current window,
        // shift L to one position after its previous occurrence
        if (lastSeen[ch] >= L)
            L = lastSeen[ch] + 1;

        lastSeen[ch] = R;              // update last seen position

        // new window size = R - L + 1
        ans = Math.max(ans, R - L + 1);
    }
    return ans;
}
}