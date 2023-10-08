/*
Problem Description

Given a string A of parantheses  ‘(‘ or ‘)’.

The task is to find minimum number of parentheses ‘(‘ or ‘)’ (at any positions) we must add to make the resulting parentheses string valid.

An string is valid if:

Open brackets must be closed by the corresponding closing bracket.
Open brackets must be closed in the correct order.


Problem Constraints
1 <= |A| <= 105

A[i] = '(' or A[i] = ')'



Input Format
First and only argument is an string A.



Output Format
Return a single integer denoting the minimumnumber of parentheses ‘(‘ or ‘)’ (at any positions) we must add in A to make the resulting parentheses string valid.



Example Input
Input 1:

 A = "())"
Input 2:

 A = "((("


Example Output
Output 1:

 1
Output 2:

 3


Example Explanation
Explanation 1:

 One '(' is required at beginning.
Explanation 2:

 Three ')' is required at end.
*/
public class Solution {
    public int solve(String A) {
        int size = A.length();
        if(size == 0)
            return 0;
        else if(size ==1)
            return 1;
        
        int leftBraces = 0, rightBraces = 0;
        for(int i =0; i < size ; i++){
            if(A.charAt(i) == '('){
                leftBraces++;
            }
            else if(A.charAt(i) == ')'){
                if(leftBraces > 0)
                    leftBraces--;
                else    
                    rightBraces++;
            }   
        }        
        return leftBraces + rightBraces;
    }
