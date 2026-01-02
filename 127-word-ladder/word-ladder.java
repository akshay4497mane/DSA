import java.util.*;

class Pair {
    String word;
    int steps;

    Pair(String word, int steps) {
        this.word = word;
        this.steps = steps;
    }
}

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return 0;

        Queue<Pair> q = new ArrayDeque<>();
        q.offer(new Pair(beginWord, 1));
        dict.remove(beginWord);

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            String word = cur.word;
            int steps = cur.steps;

            if (word.equals(endWord)) return steps;

            char[] arr = word.toCharArray();
            for (int i = 0; i < arr.length; i++) {
                char original = arr[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    if (c == original) continue;
                    arr[i] = c;
                    String next = new String(arr);

                    if (dict.contains(next)) {
                        dict.remove(next);
                        q.offer(new Pair(next, steps + 1));
                    }
                }
                arr[i] = original;
            }
        }
        return 0;
    }
}
