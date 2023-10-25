/*
Given a 0-indexed string s, permute s to get a new string t such that:
All consonants remain in their original places. More formally, if there is an index i with 0 <= i < s.length such that s[i] is a consonant, then t[i] = s[i].
The vowels must be sorted in the nondecreasing order of their ASCII values. More formally, for pairs of indices i, j with 0 <= i < j < s.length such that s[i] and s[j] are vowels, then t[i] must not have a higher ASCII value than t[j].
Return the resulting string.
The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in lowercase or uppercase. Consonants comprise all letters that are not vowels.
https://leetcode.com/contest/biweekly-contest-109/problems/sort-vowels-in-a-string/
Input: s = "lEetcOde"
Output: "lEOtcede"
Explanation: 'E', 'O', and 'e' are the vowels in s; 'l', 't', 'c', and 'd' are all consonants. The vowels are sorted according to their ASCII values, and the consonants remain in the same places.
*/

class Solution {
    Character[] vow = {'a','e','i','o','u','A','E','I','O','U'};
    boolean isVowel(char c){
        return Arrays.asList(vow).contains(c);
    }
    public String sortVowels(String s) {
        StringBuilder ans = new StringBuilder();
        List<Character> vowels = new ArrayList<>();
        for(int i=0;i<s.length();i++)
            if( isVowel(s.charAt(i)))
                vowels.add(s.charAt(i));
        Collections.sort(vowels);
        int j=0;
        for(int i=0; i<s.length();i++){
            if( isVowel(s.charAt(i)))
                ans.append(vowels.get(j++));
            else
                ans.append(s.charAt(i));
        }
        return ans.toString();
    }
}
