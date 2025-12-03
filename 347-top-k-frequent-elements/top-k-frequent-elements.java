class Solution {
    /*Approach 1:
    Priority Queue int[]  freq, value 
    Time complexity : O(N+Nlogk)
    Space complexity : O(N+k)    
    nlogn

    Freq map
    List< Freq , List<Integer>>   freq -> values list

    Time complexity: O(N)
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for(int num : nums){
            freq.put(num, 1+freq.getOrDefault(num, 0));
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
        for(Map.Entry<Integer, Integer> pair : freq.entrySet()){
            pq.add(new int[]{pair.getKey(), pair.getValue()});
            if(pq.size()>k)
                pq.poll();
        }
        /*
        System.out.println("PQ Heap:");
        while(!pq.isEmpty()){
            System.out.print(pq.remove()[0] + " ");
        }
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
}