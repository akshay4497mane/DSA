class Solution {
    /*Approach 1: Using Priority Queue
    Time complexity : O(N+Nlogk)
    Space complexity : O(N+k)    
    1. HashMap ( num -> freq)
    2. Min Heap of size k /Priority Queue to store top K
    Time complexity: O(N + N logK )
     */

     //Approach 1 : using min Heap of int[] num, freq
    public int[] topKFrequent_1(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for(int num : nums){ //O(N)
            freq.put(num, 1+freq.getOrDefault(num, 0));
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
        for(Map.Entry<Integer, Integer> pair : freq.entrySet()){ //O(N log K)
            pq.add(new int[]{pair.getKey(), pair.getValue()});
            if(pq.size()>k)
                pq.poll();
        }
        /*
        System.out.println("PQ Heap:");
        while(!pq.isEmpty())
            System.out.print(pq.remove()[0] + " ");
        System.out.println("Freq Map:");
        freq.forEach( (key,val) -> System.out.println( key + "-" + val));
        */
        int[] ans = new int[k];
        int ind =k-1;
        while(!pq.isEmpty()){
            ans[ind--] = pq.poll()[0];
        }
        return ans;
    }

    //Approach 2 : using min Heap of Integers(num) | using freq Map itself
    public int[] topKFrequent_2(int[] nums, int k) {
        if (k == nums.length) {// O(1) time
            return nums;
        }
        // 1. Build hash map: character and how often it appears
        Map<Integer, Integer> count = new HashMap();
        for (int n: nums) {  // O(N) time
          count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // init min heap 'the less frequent element first'
        Queue<Integer> heap = new PriorityQueue<>(
            (n1, n2) -> count.get(n1) - count.get(n2));

        // 2. Keep k top frequent elements in the heap
        // O(N log k) < O(N log N) time
        for (int n: count.keySet()) {
          heap.add(n);
          if (heap.size() > k) 
            heap.poll();    
        }

        // 3. Build an output array
        // O(k log k) time
        int[] top = new int[k];
        for(int i = k - 1; i >= 0; --i) {
            top[i] = heap.poll();
        }
        return top;
    }
    /* 
    Approach 3 : using Bucket Sort | O(N)

    */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for(int num : nums)
            freq.put(num, 1+freq.getOrDefault(num,0));
        int N = nums.length;
        List<Integer>[] freqArr = new ArrayList[N+1];
        for(Map.Entry<Integer,Integer> entry : freq.entrySet()){
            int num = entry.getKey(), numFreq = entry.getValue();
            if(freqArr[numFreq] == null)
                freqArr[numFreq] = new ArrayList<>();
            freqArr[numFreq].add(num);
        }
        int[] ans = new int[k];
        int ansInd = 0;
        for(int i = N; i>=0; i--){
            if(freqArr[i] != null){
                for(Integer num : freqArr[i]){
                    ans[ansInd++] = num;
                    if(ansInd==k) return ans;
                }
            }
        }
        return new int[2];
    }
}