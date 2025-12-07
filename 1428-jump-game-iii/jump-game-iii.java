class Solution {
    /* BFS
    Time O(N) | Space : O(N) 
    With Boolean Visited Array
    */
    public boolean canReach_1(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.offer(start); visited[start] = true;
        while (!q.isEmpty()){
            int i = q.poll();
            //visited[i]=true;
            if (arr[i] == 0) return true; // If found zero → success
            int forward = i + arr[i]; // Forward jump
            if (forward < n && !visited[forward]) {
                q.offer(forward); visited[forward] = true;
            }
            int backward = i - arr[i]; // Backward jump
            if (backward >= 0 && !visited[backward]) {
                q.offer(backward); visited[backward] = true;
            }
        }
        return false;
    }
    //Without Boolean array | Use -1 * arr[i]  to mark visited
    public boolean canReach_2(int[] arr, int start) {
        int n = arr.length;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        while (!q.isEmpty()) {
            int node = q.poll();
            if (arr[node] == 0) // check if reach zero
                return true;
            if (arr[node] < 0)  //checking if already visited
                continue;
            // check available next steps
            if (node + arr[node] < n) {
                q.offer(node + arr[node]);
            }
            if (node - arr[node] >= 0) {
                q.offer(node - arr[node]);
            }
            arr[node] = -arr[node];// mark as visited
        }
        return false;
    }

    //DFS 
    public boolean canReach(int[] arr, int start) {
        if (start<0 || start >= arr.length || arr[start] < 0) return false;  //BASE Case
        if (arr[start] == 0) return true;
        arr[start] = -arr[start];
        return ( canReach(arr, start + arr[start]) || canReach(arr, start - arr[start]));
    }
}