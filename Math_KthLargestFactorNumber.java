/*
Asked in Salesforce OA

Given a number A, find all factors of A.
https://www.interviewbit.com/problems/all-factors/

Given two positive integers N and K, the task is to print the Kth largest factor of N.  
https://www.geeksforgeeks.org/kth-largest-factor-of-number-n/
Input: N = 12, K = 3
Output: 4
Explanation: The factors of 12 are {1, 2, 3, 4, 6, 12}. The largest factor is 12 and the 3rd largest factor is 4.
Input: N = 30, K = 2
Output: 15
Explanation: The factors of 30 are {1, 2, 3, 5, 6, 10, 15, 30} and the 2nd largest factor is 15.


*/

public class Solution {
    public ArrayList<Integer> allFactors(int A) {
        ArrayList<Integer> ans = new ArrayList<>();
        int i = 1;
        for( ; i*i <= A ; i++){
            if(A%i == 0){
                ans.add(i);
                if(i*i != A)
                    ans.add((int) A/i);
            }
        }
        Collections.sort(ans);
        return ans;
    }
}
