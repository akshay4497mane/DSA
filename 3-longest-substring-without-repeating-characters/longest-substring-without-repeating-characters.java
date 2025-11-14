class Solution {
/*Sliding Window, Untill window has unique characters
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