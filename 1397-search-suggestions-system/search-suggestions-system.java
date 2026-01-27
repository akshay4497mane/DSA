/*
🧩 Problem Explanation (3–4 lines)

You are given a list of product names and a searchWord that is typed character by character.
After each character is typed, you must suggest up to 3 products that start with the current prefix.
If more than 3 products match, return the lexicographically smallest 3.
You must return suggestions for every prefix of the search word.

🧪 Example (from the problem statement)
Input
products = ["mobile","mouse","moneypot","monitor","mousepad"]
searchWord = "mouse"

Step 1: Sort products
["mobile","moneypot","monitor","mouse","mousepad"]

Prefix-wise Suggestions
Typed Prefix	Suggestions
"m"	["mobile","moneypot","monitor"]
"mo"	["mobile","moneypot","monitor"]
"mou"	["mouse","mousepad"]
"mous"	["mouse","mousepad"]
"mouse"	["mouse","mousepad"]
🧠 Approach 1: Brute Force
💡 Intuition / Summary

For each prefix of searchWord, scan all products, keep those that start with the prefix, sort them, and return the first 3.
This is the most straightforward approach but repeats work many times.

⏱️ Time & Space Complexity

Time: O(L × N log N)

Space: O(N)
Where L = length of searchWord, N = number of products

❌ Drawback

Repeated scanning and sorting makes it inefficient.

⚡ Approach 2: Sort Once + Binary Search (Optimal)
💡 Intuition / Summary

Sort the products once.

For each prefix:

Use binary search to find the first product ≥ prefix.

From that index, check only the next 3 products.

This avoids scanning the entire list for every prefix.

This is the most preferred interview solution.

⏱️ Time & Space Complexity

Sorting: O(N log N)

Each prefix: O(log N + 3)

Total: O(N log N + L log N)

Space: O(1) (excluding output)

✅ Clean Java Code (Line-by-Line Commented)
import java.util.*;

class Solution {

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {

        // Step 1: Sort products lexicographically
        Arrays.sort(products);

        List<List<String>> result = new ArrayList<>();
        String prefix = "";

        // Step 2: Process each character of searchWord
        for (char ch : searchWord.toCharArray()) {

            // Build current prefix
            prefix += ch;

            // Find first index where product >= prefix
            int start = lowerBound(products, prefix);

            List<String> suggestions = new ArrayList<>();

            // Collect at most 3 matching products
            for (int i = start; i < products.length && suggestions.size() < 3; i++) {

                // Stop if prefix no longer matches
                if (!products[i].startsWith(prefix)) break;

                suggestions.add(products[i]);
            }

            result.add(suggestions);
        }

        return result;
    }

    // Binary search to find lower bound
    private int lowerBound(String[] products, String target) {

        int low = 0, high = products.length;

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (products[mid].compareTo(target) < 0)
                low = mid + 1;
            else
                high = mid;
        }

        return low;
    }
}

*/

/*
✅ Approach 3: Trie (Advanced / Optional)
💡 Intuition (1–2 lines you can say)

We insert all products into a Trie.
Each Trie node stores up to 3 lexicographically smallest products for that prefix, so suggestions are instantly available while typing.

⏱️ Time & Space Complexity

Build Trie: O(total characters)

Query: O(L) where L = searchWord length
Space: O(total characters)

*/

class Solution {

    // Trie Node definition
    static class TrieNode {
        TrieNode[] children = new TrieNode[26]; // for 'a' to 'z'
        List<String> suggestions = new ArrayList<>(); // top 3 products
    }

    TrieNode root = new TrieNode();

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {

        // Sort products so lexicographically smaller ones come first
        Arrays.sort(products);

        // Insert each product into Trie
        for (String product : products) {
            insert(product);
        }

        List<List<String>> result = new ArrayList<>();
        TrieNode curr = root;

        // Traverse Trie for each character of searchWord
        for (char ch : searchWord.toCharArray()) {

            int idx = ch - 'a';

            // If path breaks, no further suggestions possible
            if (curr != null && curr.children[idx] != null) {
                curr = curr.children[idx];
                result.add(curr.suggestions);
            } else {
                curr = null;
                result.add(new ArrayList<>());
            }
        }

        return result;
    }

    // Insert word into Trie
    private void insert(String word) {

        TrieNode node = root;

        for (char ch : word.toCharArray()) {

            int idx = ch - 'a';

            // Create node if not present
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }

            node = node.children[idx];

            // Store only top 3 lexicographically smallest products
            if (node.suggestions.size() < 3) {
                node.suggestions.add(word);
            }
        }
    }
}

/*
🗣️ How to Explain This in Interview (Perfect Answer)
“I insert all products into a Trie after sorting them. Each Trie node stores the top three lexicographically smallest products for that prefix. While typing the search word, I traverse the Trie and directly fetch suggestions in O(1) per character.”
*/