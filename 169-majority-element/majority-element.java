class Solution {
//majority element — the number that appears more than n/2 times in an array.
/*
Approach 1: Brute force | For each num | count occurrences and return if > n/2
Time : O(N^2) | Space : O(N)
*/
    public int majorityElement_1(int[] A) {
        int majority = A.length/2;
        for(int num : A){
            int count =0;
            for(int ele : A){
                if(num == ele) count++;
            }
            if(count > majority)
                return num;
        }
        return -1;
    }
/*
Approach 2: Use hashmap to store counts
*/
    public int majorityElement(int[] A) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : A){
            map.put( num, map.getOrDefault(num, 0)+1);
        }
        int majority = A.length/2;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() > majority)
                return entry.getKey();
        }
        return -1;        
    }

/*
Approach : Boyer–Moore Voting Algorithm | Time: O(N), Space : O(1)
It works because of vote cancellation logic (Boyer–Moore algorithm):
You keep a candidate (x) and a count (xCount).
-> When the same number appears, you increase the count.
-> When a different number appears, you decrease it.
-> If the count becomes zero, you reset the candidate.
Each different element cancels one vote from the current candidate.
Since the majority element appears more than half the time, it can’t be fully canceled — it will always remain as the final candidate.
Result → the last surviving x is the majority element.
*/
    public int majorityElement_4(int[] A) {
        int candidate = 0, count = 0;
        for (int num : A) {
            if (count == 0) 
                candidate = num;
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }
    public int majorityElement_5(int[] A) {
        int x = 0, xCount = 0;
        for(int i=0; i<A.length; i++){
            if(xCount==0){
                x = A[i];
                xCount++;
            }else if(x == A[i]){
                xCount++;
            }else{
                xCount--;
            }
        }
        return x;        
    }
}
