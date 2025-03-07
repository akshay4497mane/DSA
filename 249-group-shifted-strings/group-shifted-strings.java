class Solution {
    /**
     * Generates a hash key based on the difference between adjacent characters.
     * Ensures that strings with the same shifting pattern have the same key.
     * 
     * Example:
     * "abc" -> "a" (diffs: b-a=1, c-b=1) -> "aa"
     * "bcd" -> "a" (diffs: c-b=1, d-c=1) -> "aa" (same key as "abc", so they belong in the same group)
     * 
     * @param s the input string
     * @return the hash key representing the shifting pattern
     * 
     * Time Complexity: O(M), where M is the length of the string
     * Space Complexity: O(M) for storing the hash key
     */
    String getHash(String s) {
        char[] chars = s.toCharArray();
        StringBuilder hashKey = new StringBuilder();

        // Compute difference between adjacent characters
        for (int i = 1; i < chars.length; i++) {
            hashKey.append((char) ((chars[i] - chars[i - 1] + 26) % 26 + 'a' - 1));
            // (a - b) % 26 can be negative if a < b, so we add 26 to ensure non-negative value
        }
        return hashKey.toString();
    }

    /**
     * Groups strings that belong to the same shifting sequence.
     * 
     * Approach:
     * 1. Compute a hash key for each string using `getHash()`.
     * 2. Store strings with the same hash key in a HashMap.
     * 3. Convert the HashMap values to a list of grouped strings.
     * 
     * @param strings the input array of strings
     * @return a list of grouped strings
     * 
     * Time Complexity: O(N * M)
     *   - N = number of strings
     *   - M = average length of each string
     *   - `getHash()` runs in O(M) per string, and we process N strings → O(N * M)
     * 
     * Space Complexity: O(N * M)
     *   - HashMap stores up to N keys, each of size at most M → O(N * M)
     *   - Lists inside the map store all N strings → O(N)
     *   - The final output list takes O(N)
     */
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        
        for (String s : strings) {
            // Efficiently initializes the list if key is absent and adds the string
            map.computeIfAbsent(getHash(s), key -> new ArrayList<>()).add(s);

            // Alternative approach using explicit if-check (kept for reference)
            /*
            String hashKey = getHash(s);
            if (!map.containsKey(hashKey)) {
                map.put(hashKey, new ArrayList<>());
            }
            map.get(hashKey).add(s);
            */
        }

        // Direct conversion of map values to list
        return new ArrayList<>(map.values());

        // Alternative manual conversion (kept for reference)
        /*
        List<List<String>> ans = new ArrayList<>();
        for (List<String> stringGroup : map.values()) {
            ans.add(stringGroup);
        }
        return ans;
        */
    }
}
