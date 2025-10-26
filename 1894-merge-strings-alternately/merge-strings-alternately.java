class Solution {
    //Time complexity: O(m+n)
    public String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        int i=0, j=0, len1=word1.length(), len2=word2.length();
        while(i<len1 || j<len2){
            if(i<len1)sb.append(word1.charAt(i++));
            if(j<len2)sb.append(word2.charAt(j++));
        }
        return sb.toString();
    }
}