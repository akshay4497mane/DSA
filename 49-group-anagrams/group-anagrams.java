/*
This code groups anagrams from a list of strings by using a frequency-based key.
Time Complexity: O(N * K), where N is the number of strings and K is the average length of a string. 
Space Complexity: O(N * K) for storing grouped anagrams and frequency arrays.
*/

class Solution {
    public List<List<String>> groupAnagrams(String[] words) {
        int[] charFrequency = new int[26]; // Array to store character frequencies for each word.
        Map<String, ArrayList<String>> groupedAnagrams = new HashMap<>(); // Map to group words by their frequency-based key.

        for(String word : words) {
            Arrays.fill(charFrequency, 0); // Reset frequency array for each word.
            for(char character : word.toCharArray()) {
                charFrequency[character - 'a']++; // Count occurrences of each character.
            }

            StringBuilder keyBuilder = new StringBuilder(); // Create a key from the frequency array.
            for(int frequency : charFrequency) {
                keyBuilder.append(frequency); // Append frequency of the character.
                keyBuilder.append("#"); // Add separator for unique representation.
            }
            String key = keyBuilder.toString(); // Frequency-based key for the word.

            if(!groupedAnagrams.containsKey(key)) { // If key doesn't exist, create a new entry.
                groupedAnagrams.put(key, new ArrayList<>());
            }
            groupedAnagrams.get(key).add(word); // Add the word to the corresponding group.
        }
        return new ArrayList<>(groupedAnagrams.values()); // Return the grouped anagrams as a list of lists.
    }
}