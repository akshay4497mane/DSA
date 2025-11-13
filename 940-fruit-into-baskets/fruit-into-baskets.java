class Solution {
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> map = new HashMap<>(); //fruit, count
        int L=0, R=0, maxAns=0, N = fruits.length;
        while(R<N){
            map.put(fruits[R], map.getOrDefault(fruits[R], 0)+1);

            while(map.size()>2){
                int LCount = map.get(fruits[L]);
                if(LCount>1){
                    map.put(fruits[L], LCount-1 );
                }else{
                    map.remove(fruits[L]);
                }
                L++;
            }
            //if(map.size()<=2)
            maxAns = Math.max(maxAns, R-L+1);
            R++;
        }
        return maxAns;
    }
}
