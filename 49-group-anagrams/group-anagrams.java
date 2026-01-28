/*
Group Anagrams – Ultra Short Notes (with examples)

Problem
Group words that are anagrams (same character counts).

Example
Input: ["eat","tea","tan","ate","nat","bat"]

Output: [["eat","tea","ate"], ["tan","nat"], ["bat"]]

Key idea : Anagrams share the same character frequency.

Approach
For each word, count letters (size 26 array)
Convert count array to a fixed key
Use HashMap<key, list of words>
Why this works
"eat", "tea", "ate" → same counts → same key

Time Complexity
O(N * K)
N = number of words, K = word length

Space Complexity
O(N * K) output + O(N) keys

Edge cases

[""] → [[""]]

["a","a"] → [["a","a"]]

["abc"] → [["abc"]]

One-liner for interview
“Group strings by character frequency using a hash map for optimal O(N*K) time.”

*/

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> ans = new ArrayList<>();
        for(String s : strs){
            int[] freq = new int[26];
            for(Character ch : s.toCharArray()){
                freq[ch-'a']++;
            }
            StringBuilder keySb = new StringBuilder();
            for(int i=0; i<26; i++)
                keySb.append("#").append(freq[i]);
            String key = keySb.toString();
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(s);
        }
        return new ArrayList(map.values());
    }
}
/*
Why # is used
The separator prevents different frequency arrays from forming the same key string.

What goes wrong without #?
Example (collision)
Freq arrays:

Word1: "abbbbbbbbbbb"
a=1, b=11

Word2: "aaaaaaaaaaab"
a=11, b=1

If key is built without separator:

111000...


Both produce the same string → WRONG grouping

With separator:

#1#11#0...
#11#1#0...


Keys are different → correct

Another simple example
Counts:

[1, 0, 11]

[10, 1, 1]

Without separator → "1011"
Ambiguous

Why char[] key avoids this problem

char[] freq = new char[26];
String key = new String(freq);


Fixed length

Each index maps to one character

No ambiguity

No separator needed

Interview takeaway

Separator is mandatory when serializing numbers into a string

Fixed-size array keys are safer and faster

One-liner to say
“Without a separator, frequency encoding becomes ambiguous and causes hash collisions.”
*/