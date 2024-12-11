class WordDistance {
    Map<String, List<Integer>> m = new HashMap<>();
    public WordDistance(String[] wordsDict) {
        int N= wordsDict.length;
        for(int i=0; i< N; i++ ){
            if(!m.containsKey(wordsDict[i]))
                m.put( wordsDict[i], new ArrayList<>() );
            m.get(wordsDict[i]).add(i);
        }
    }
    
    public int shortest(String word1, String word2) {
        System.out.println("shortest(" + word1 + "," + word2);
        List<Integer> word1Indices = m.get(word1);
        List<Integer> word2Indices = m.get(word2);
        System.out.println(word1Indices + " \n " + word2Indices);
        int ansShortestDist = Integer.MAX_VALUE;
        int i1=0, i2=0, word1IndicesSize = word1Indices.size(), word2IndicesSize = word2Indices.size();
        while( i1 < word1IndicesSize && i2 < word2IndicesSize){
            System.out.println(i1 + " - " + i2);

            ansShortestDist = Math.min(ansShortestDist, Math.abs(word1Indices.get(i1)-word2Indices.get(i2)));
            if( word1Indices.get(i1) < word2Indices.get(i2) ){
                i1++;
            }else{
                i2++;
            }
        }
        return ansShortestDist;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(wordsDict);
 * int param_1 = obj.shortest(word1,word2);
 */