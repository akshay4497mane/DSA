class Solution {
    public String minWindow(String s, String t) {
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
}