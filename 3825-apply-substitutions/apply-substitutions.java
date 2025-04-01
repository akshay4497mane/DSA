class Solution {
    // A map to store the key-value pairs for substitutions
    // Key: Placeholder (e.g., "A", "B", "C"), Value: Substitution string (e.g., "bce", "ace", etc.)
    Map<String, String> map = new HashMap<>();
    
    /**
     * Main function to apply substitutions to the input text.
     * @param replacements List of replacement pairs where each pair is [key, value].
     * @param text The input text where placeholders need to be replaced with corresponding values.
     * @return The modified text after applying all substitutions.
     */
    public String applySubstitutions(List<List<String>> replacements, String text) {
        // Loop through the replacement list and populate the map with substitutions
        for (List<String> s : replacements) {
            map.put(s.get(0), s.get(1));  // Store each key-value pair in the map
        }
        
        // Process the input text to replace placeholders using the map
        return processStr(text);
    }

    /**
     * Helper method to process and replace placeholders in the string.
     * If a placeholder is found, it substitutes the corresponding value from the map.
     * If the replacement string contains further placeholders, it processes those recursively.
     * @param s The input string to process.
     * @return The string with all placeholders replaced by corresponding values.
     */
    String processStr(String s) {
        StringBuilder sb = new StringBuilder();  // StringBuilder to build the modified string efficiently
        
        // Iterate through each character in the input string
        for (int i = 0; i < s.length(); i++) {
            // If the current character is '%', it indicates a placeholder
            if (s.charAt(i) == '%') {
                // Extract the key by looking at the character after '%'
                String key = String.valueOf(s.charAt(i + 1));
                
                // Recursively substitute the placeholder's value
                String newVal = processStr(map.get(key));  // Get the replacement value and process it
                
                // Append the new value to the result
                sb.append(newVal);
                
                // Skip over the '%' and the key (next two characters)
                i++;  // Skip '%'
                i++;  // Skip the key character
            } else {
                // If it's not a placeholder, append the character as is to the result
                sb.append(s.charAt(i));
            }
        }
        
        // Return the fully processed string
        return sb.toString();
    }
}
