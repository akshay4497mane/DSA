class WordDistance {
    // Map to store word as key and a list of indices where the word appears
    Map<String, List<Integer>> wordIndexMap = new HashMap<>();

    // Constructor to initialize the map with the given words array
    public WordDistance(String[] wordsDict) {
        // Loop through each word in the input array and store its indices
        for (int i = 0; i < wordsDict.length; i++) {
            // If the word is not already in the map, initialize a new list for it
            wordIndexMap.computeIfAbsent(wordsDict[i], k -> new ArrayList<>()).add(i);
        }
    }

    // Method to return the shortest distance between word1 and word2
    public int shortest(String word1, String word2) {
        // Get the list of indices for both words
        List<Integer> word1Indices = wordIndexMap.get(word1);
        List<Integer> word2Indices = wordIndexMap.get(word2);

        // Initialize the shortest distance to a large value
        int shortestDistance = Integer.MAX_VALUE;

        // Use two pointers to traverse the index lists of word1 and word2
        int i1 = 0, i2 = 0;
        int word1Size = word1Indices.size();
        int word2Size = word2Indices.size();

        // Iterate through the lists to find the minimum distance
        while (i1 < word1Size && i2 < word2Size) {
            // Update the shortest distance with the minimum of the current and previous distances
            shortestDistance = Math.min(shortestDistance, Math.abs(word1Indices.get(i1) - word2Indices.get(i2)));

            // Move the pointer of the word that appears first to try and find a closer pair
            if (word1Indices.get(i1) < word2Indices.get(i2)) {
                i1++;
            } else {
                i2++;
            }
        }
        return shortestDistance;
    }
}

/**
 * Usage example:
 * WordDistance obj = new WordDistance(wordsDict);
 * int param_1 = obj.shortest(word1, word2);
 */
