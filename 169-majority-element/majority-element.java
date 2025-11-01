class Solution {
/*
Boyer–Moore Voting Algorithm
majority element — the number that appears more than n/2 times in an array.

It works because of vote cancellation logic (Boyer–Moore algorithm):
You keep a candidate (x) and a count (xCount).
-> When the same number appears, you increase the count.
-> When a different number appears, you decrease it.
-> If the count becomes zero, you reset the candidate.
Each different element cancels one vote from the current candidate.
Since the majority element appears more than half the time, it can’t be fully canceled — it will always remain as the final candidate.
Result → the last surviving x is the majority element.
*/
    public int majorityElement(int[] A) {
        int candidate = 0, count = 0;
        for (int num : A) {
            if (count == 0) 
                candidate = num;
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }

    public int majorityElement_1(int[] A) {
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
