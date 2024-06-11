class Solution {
/*
For every number N => calculate sumDigits,  whenever sumDigits becomes 1, happy number | else use HashSet to maintain track of already used Numbers
Time : O(1)
Space : O(1)
*/
public boolean isHappy(int n) {
    HashSet<Integer> usedNumbers = new HashSet<>();
    int sumDigits = 0;
    while( true ){
        sumDigits = 0;
        while(n!=0){ //calculate sum of digits
            sumDigits += Math.pow( n%10, 2);
            n = n/10;
        }
        if(sumDigits == 1) return true; //Happy Number Found
        if(usedNumbers.contains(sumDigits)) return false; //Unhappy Number
        n = sumDigits;
        usedNumbers.add(sumDigits);
    }
}
}