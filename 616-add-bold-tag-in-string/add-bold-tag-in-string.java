class Solution {
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