class Solution {
/*
Odd can be done only once. Eg. 1 * 2 => become even 
Even may be applied multiple times, 400 => 200 => 100=> 50 => 25

*/

public int minimumDeviation(int[] nums) {
    PriorityQueue<Integer> maxHeap=new PriorityQueue<>( (a,b)-> b-a );
//new PriorityQueue<>((a, b) -> Integer.compare(b.value, a.value));
    int min=Integer.MAX_VALUE, max=Integer.MIN_VALUE;
        for(Integer num : nums){
            if( num%2 != 0 ) num = num * 2;
            maxHeap.add(num);
            min = Math.min(min, num);
        }
        int diff = max-min;
        while(maxHeap.peek() % 2 == 0 ){
            max = maxHeap.remove();
            diff = Math.min( diff, max-min);
            min = Math.min( min, max/2);
            maxHeap.add(max/2);
        }
        max = maxHeap.remove();
        diff = Math.min( diff, max-min);
    return diff;
}
}