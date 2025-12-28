class Solution {
    /*
    https://youtu.be/taYRJf-M25I?si=KArw7Lix0HM8q88f
    */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if(s==null || words == null || words.length==0) return ans;
        int wordCount = words.length, wordLen = words[0].length(), windowSize = wordCount * wordLen;
        if(windowSize > s.length()) return ans;
        Map<String, Integer> freqMap = new HashMap<>();
        for(String word : words)
            freqMap.put(word, freqMap.getOrDefault(word, 0)+1);
        for(int i=0; i<wordLen; i++){ //run sliding window 3 times for each char in word len "foo"
            int left = i, right = i;
            Map<String, Integer> currSeenMap = new HashMap<>();
            int matched = 0;
            while( right + wordLen <= s.length() ){
                String currRightWord = s.substring(right, right+wordLen);
                right = right + wordLen;
                if(!freqMap.containsKey(currRightWord)){  //window is useless, reset Window
                    left = right; //right already on next word
                    currSeenMap.clear();
                    matched = 0;
                    continue;
                }
                //word is found as per map
                //add to CurrSeenMap
                currSeenMap.put( currRightWord, currSeenMap.getOrDefault(currRightWord, 0)+1);
                matched++;
                //check if window is valid
                while( currSeenMap.get(currRightWord) > freqMap.get(currRightWord) ){
                    String currLeftWord = s.substring(left, left+wordLen);
                    currSeenMap.put(currLeftWord, currSeenMap.get(currLeftWord) - 1);
                    matched--;
                    left = left + wordLen;
                }
                if( matched == wordCount)
                    ans.add(left);
            }
        }
        return ans;
    }
}
