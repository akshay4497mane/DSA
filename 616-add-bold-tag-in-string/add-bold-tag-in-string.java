class Solution {
/* 
Time complexity: 
To calculate bold, we iterate over words. 
For each word, we use indexOf() which costs O(n⋅k). 
However, we may call it multiple times per word. 
In the worst case scenario, such as s = "aaaaa...aaaaa" and word = "aaaaaa", it may be called O(n−k) times.
Each word could cost us O((n−k)⋅n⋅k)

There are m words, which means calculating bold could cost 
O(m * (n−k) *  n*k)

After calculating bold, we create the answer in O(n). This work is dominated by the other terms.

Space complexity: O(n) | We use the boolean array bold which has a length of n.
*/
    public String addBoldTag(String s, String[] words) {
        int n = s.length();
        boolean[] bold = new boolean[n];
        for( String word : words){
            int startInd = s.indexOf(word);
            while( startInd != -1 ){
                for( int i=startInd; i<startInd+word.length(); i++ ){
                    bold[i] = true;                    
                }
                startInd = s.indexOf(word, startInd+1);
            }
        }
        String openTag = "<b>";
        String closeTag = "</b>";
        StringBuilder ans = new StringBuilder();
        for ( int i=0; i<n; i++ ){
            if( bold[i] && (i==0 || !bold[i-1]))
                ans.append(openTag);
            ans.append(s.charAt(i));
            if( bold[i] && (i==n-1 || !bold[i+1]))
                ans.append(closeTag);
        }
        return ans.toString();
    }
}