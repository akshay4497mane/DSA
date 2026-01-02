import java.util.*;

class Solution {

    Map<String, Set<String>> parent = new HashMap<>();
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

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

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

                        if (!dict.contains(next)) continue;

                        parent
                            .computeIfAbsent(next, x -> new HashSet<>())
                            .add(word);

                        if (levelVisited.add(next)) {
                            if (next.equals(endWord)) found = true;
                            q.offer(next);
                        }
                    }
                    arr[i] = old;
                }
            }
            dict.removeAll(levelVisited); // CRITICAL
            visited.addAll(levelVisited);
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
