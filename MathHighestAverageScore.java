/*
Highest Score
Programming
Math
Medium
77.0% Success

69

14

Bookmark
Asked In:
Problem Description
 
 

You are given a 2D string array A of dimensions N x 2,

where each row consists of two strings: first is the name of the student, second is their marks.

You have to find the maximum average mark. If it is a floating point, round it down to the nearest integer less than or equal to the number.



Problem Constraints
1 <= N <= 105


Input Format
The first argument is a 2D string array A.


Output Format
Return a single integer which is the highest average mark.


Example Input
Input 1:
A = [["Bob", "80"], ["Bob", "90"], ["Alice", "90"]]
Input 2:

A = [["Bob", "80"], ["Bob", "90"], ["Alice", "90"], ["Alice", "10"]]


Example Output
Output 1:
90
Output 2:

85

*/


public class Solution {
    public int highestScore(ArrayList<ArrayList<String>> A) {
        HashMap<String, ArrayList<Integer>> hm = new HashMap<>();
        
        for(ArrayList<String> a : A){
            ArrayList<Integer> val;            
            if( hm.containsKey(a.get(0)) ){
                val = hm.get( a.get(0) );
            }else{
                val = new ArrayList<>();
            }
            val.add( (int) Float.parseFloat( a.get(1)) );
            //val.add(Integer.parseInt( a.get(1) ));//NumberFormatException for float String inp
            //Math.round(8.8) => 9 | (int) 8.8 => 8
            hm.put(a.get(0), val);
        }
        int maxAverage = 0;
        for(Map.Entry<String, ArrayList<Integer>> entry : hm.entrySet() ){
            String key = entry.getKey();
            ArrayList<Integer> marksArray = entry.getValue();
            //System.out.println("\nkey: " + key + "Values:");
            //marksArray.forEach(System.out::print);
            int avg = (int)marksArray.stream()
                            .mapToInt(Integer::intValue)
                            .average()
                            .orElse(0.0);
            //System.out.println("\nMarks Sum: " + avg );
            if(avg > maxAverage)
                maxAverage = avg;
        }
        return maxAverage;
    }
}
