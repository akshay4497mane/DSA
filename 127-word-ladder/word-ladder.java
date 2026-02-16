/*
Word Ladder – BFS revision (copy-paste)
1. Use BFS → guarantees shortest path
2. Use Set as both dictionary + visited
3. Remove word when ENQUEUED (not dequeued)
4. Process level-by-level → steps++ after each level
5. Generate neighbors by changing one char at a time
6. Reuse same char[]; restore after mutation
7. Skip same character replacement
8. Each word visited once → avoids TLE
 */
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return 0;
        Queue<String> q = new ArrayDeque<>();
        q.offer(beginWord);
        dict.remove(beginWord);
        int steps = 1;
        while (!q.isEmpty()) {
            int levelSize = q.size();
            for (int k = 0; k < levelSize; k++) {
                String word = q.poll();
                if (word.equals(endWord)) return steps;
                char[] arr = word.toCharArray();
                for (int i = 0; i < arr.length; i++) {
                    char original = arr[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == original) continue;
                        arr[i] = c;
                        String next = new String(arr);
                        if (dict.contains(next)) {
                            dict.remove(next); //IF we DONT remove, TLE
                            q.offer(next);
                        }
                    }
                    arr[i] = original;
                }
            }
            steps++;
        }
        return 0;
    }
}