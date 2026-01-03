/*
Word Ladder II (126) — revision steps (copy-paste)
Approach :
1. Use BFS from beginWord to build ONLY shortest-path graph (DAG) | 
    bfs(beginWord, endWord, dict);
                while (!q.isEmpty() && !found) {     USE FOUND FLAG -> continue for entire level , dont exit early
2. Traverse level-by-level
3. For each valid transformation:
   * record parent (child → parent) | parent[next] = word |                             
      parent.computeIfAbsent(next, x -> new HashSet<>()).add(word);//parent(next) = word
   * allow multiple parents in same level
4. Do NOT remove words immediately
5. After finishing a level:
   * remove all levelVisited words from dict
            dict.removeAll(levelVisited); // CRITICAL
            visited.addAll(levelVisited);
6. Stop BFS after first level where endWord is found
7. Graph now contains only shortest paths
8. Use DFS backtracking from endWord → beginWord to list all paths

Why BFS + DFS
* BFS guarantees shortest length
* DFS enumerates all combinations in DAG

Time Complexity
* N = number of words
* L = word length

BFS:
* Each word visited once
* For each word: L × 26 transformations
  O(N × L)

DFS:
* Outputs all shortest paths
* Cost proportional to output size
  O(total paths × path length)

Overall:
O(N × L + output size)

Space Complexity
* dict + visited: O(N)
* parent DAG (shortest paths only): O(N)
* recursion stack (max depth = path length): O(L)
* result storage: proportional to output size

Total:
O(N + output size)

Key differences to remember

* 127 → BFS, remove on enqueue
* 126 → BFS (level-based removal) + DFS

One-line mental model
Build shortest-path DAG with BFS, then enumerate paths with DFS.


For Word Ladder II, BFS builds a DAG of shortest paths:

child → {multiple parents}

Example (your testcase):

begin = red, end = tax

parent map:
tax → [tad, tex]
tad → [ted]
tex → [ted, rex]
ted → [red]
rex → [red]

But valid paths are:
red → ted → tad → tax
red → ted → tex → tax
red → rex → tex → tax

*/
import java.util.*;
class Solution{
    Map<String, Set<String>> parent = new HashMap<>(); //hit -> hot -> dot  => (dot -> hot , hot -> hit )
    List<List<String>> res = new ArrayList<>();
    String begin;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return res;

        begin = beginWord;
        bfs(beginWord, endWord, dict);

        if (!parent.containsKey(endWord)) return res;

        List<String> path = new ArrayList<>();
        path.add(endWord);
        dfs(endWord, path);
        return res;
    }

    private void bfs(String beginWord, String endWord, Set<String> dict) {
        Queue<String> q = new ArrayDeque<>();
        q.offer(beginWord);

        boolean found = false;
        int len = beginWord.length();

        while (!q.isEmpty() && !found) {
            int size = q.size();
            Set<String> levelVisited = new HashSet<>();
            for (int s = 0; s < size; s++) {
                String word = q.poll();
                char[] arr = word.toCharArray();
                for (int i = 0; i < len; i++) {
                    char old = arr[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == old) continue;
                        arr[i] = c;
                        String next = new String(arr);
                        if ( dict.contains(next) ){
                            parent.computeIfAbsent(next, x -> new HashSet<>()).add(word);//parent(next) = word
                            if (levelVisited.add(next)) {
                                if (next.equals(endWord)) found = true;
                                q.offer(next);
                            }
                        }
                    }
                    arr[i] = old;
                }
            }
            dict.removeAll(levelVisited); // CRITICAL
        }
    }

    private void dfs(String word, List<String> path) {
        if (word.equals(begin)) {
            List<String> seq = new ArrayList<>(path);
            Collections.reverse(seq);
            res.add(seq);
            return;
        }
        for (String p : parent.get(word)) {
            path.add(p);
            dfs(p, path);
            path.remove(path.size() - 1);
        }
    }
}

//Approach 1 | Not optimal => give TLE | 33 of 38 cases passed
class Solution_Approach1{
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
         List<List<String>> ans = new ArrayList<>();
        
        // Queue data structure to store 
        // the sequence of transformations
        Queue<List<String>> q = new LinkedList<>();
        
        // Add all the words to a hashset
        Set<String> st = new HashSet<>(wordList);
        
        // Add the sequence containing starting word to queue
        q.add(new ArrayList<>(Arrays.asList(beginWord)));
        
        // Erase starting word from set if it exists
        st.remove(beginWord);
        
        // Set to store the words that must be deleted 
        // after traversal of a level is complete
        Set<String> toErase = new HashSet<>();
        
        // Until the queue is empty
        while (!q.isEmpty()) {
            
            // Size of queue
            int size = q.size();
            
            // Traversing all words in current level
            for (int i = 0; i < size; i++) {
                
                // Sequence 
                List<String> seq = q.poll();
                
                // Last added word in sequence
                String word = seq.get(seq.size() - 1);
                
                // If the last word same as end word
                if (word.equals(endWord)) {
                    // Add the sequence to the answer
                    if (ans.isEmpty()) {
                        ans.add(new ArrayList<>(seq));
                    }
                    else if (ans.get(ans.size() - 1).size() == 
                             seq.size()) {
                        ans.add(new ArrayList<>(seq));
                    }
                }
                
                // Iterate on every character
                for (int pos = 0; pos < word.length(); pos++) {
                    
                    // Original letter
                    char original = word.charAt(pos);
                    
                    // Replacing current character with
                    // letters from 'a' to 'z' to match 
                    // any possible word from set
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        char[] wordArray = word.toCharArray();
                        wordArray[pos] = ch;
                        String newWord = new String(wordArray);
                        
                        // Check if it exists in the set
                        if (st.contains(newWord)) {
                            // Update the sequence
                            seq.add(newWord);
                            
                            // Push in the queue
                            q.add(new ArrayList<>(seq));
                            
                            // Add the word to erase map
                            toErase.add(newWord);
                            
                            // Backtracking step
                            seq.remove(seq.size() - 1);
                        }
                    }
                    
                    // Update the original letter back
                    String beforePos = word.substring(0, pos);
                    String afterPos = word.substring(pos + 1);
                    word = beforePos + original + afterPos;
                }
            }
            
            // Erase all the words in set after
            // traversal of a level is completed
            for (String it : toErase) st.remove(it);
            toErase.clear();
            
            // If answer is found, break
            if (!ans.isEmpty()) break;
        }
        
        // Return the result found
        return ans;
    }
    //    to reach from start word to target word */
  
}