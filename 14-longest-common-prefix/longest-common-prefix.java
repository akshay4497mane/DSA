class Solution {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();
        if( strs.length == 0 ) return "";
        if( strs.length == 1 ) return strs[0];

        for( int i=0; i<strs[0].length(); i++ ){
            sb.append( strs[0].charAt(i) );
            for( int j=1; j<strs.length; j++ ){
                if( i>=strs[j].length() || sb.charAt(i) != strs[j].charAt(i) ){
                    sb.deleteCharAt(i);
                    return sb.toString();
                }
            }
        }
        return sb.toString();
    }
}