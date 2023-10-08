/*
Sort by Color | Programming | Two Pointers | Medium | 58.8% Success
Problem Description
Given an array with N objects colored red, white, or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
We will use the integers 0, 1, and 2 to represent red, white, and blue, respectively.
Note: Using the library sort function is not allowed.
Problem Constraints
1 <= N <= 1000000
0 <= A[i] <= 2
Input Format
First and only argument of input contains an integer array A.
Output Format
Return an integer array in asked order
Example Input
Input 1 :
    A = [0 1 2 0 1 2]
Input 2:
    A = [0]
Example Output
Output 1:
    [0 0 1 1 2 2]
Output 2:

    [0]
Example Explanation
Explanation 1:
    [0 0 1 1 2 2] is the required order.
*/

public class Solution {
/*
APPROACH 1 : Time: O(N), Space O(1)
    Dutch National Flag Algorithm or "Three-Way Partitioning".
Three pointers low, high & mid
Initialize three pointers: low, mid, and high.
low will point to the position where the next 0 should be placed.
mid( i ) will iterate through the array, and 
high will point to the position where the next 2 should be placed.
*/
    void swap( ArrayList<Integer> arr, int i, int j){
        if(i == j)
            return;
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
    public void sortColors(ArrayList<Integer> arr) {
        int size = arr.size();
        if( size <= 1)
            return;
        int i = 0 , low = 0, high = size - 1;
        while ( i <= high ){
            int curr = arr.get(i);
            switch( curr ){
                case 0 : {
                    swap(arr, i , low);
                    i++;
                    low++;
                }break;
                case 1 : {
                    i++;
                }break;
                case 2 : {
                    swap(arr, i, high);
                    high--;
                }break;
            }
        } 
    }
    
    /*
    APPROACH 2 :
    Counting sort is particularly useful when the range of values in the array is limited

    */
    
    public void sortColors(ArrayList<Integer> arr) {
        int[] count = new int[3]; //count of 0,1,2
        for(int ele : arr){
            count[ ele ]++;
        }
        int i = 0, j = 0;
        arr.clear();
        
        for(int colour = 0 ; colour < count.length; colour++){
            for(i = 0; i < count[colour] ; i++){
                arr.add( colour );
            }
        }
    }
    
    
    /*APPROACH 3: Using MAP Time O(N), Space: O(N)
    */
    public static void sortColors(ArrayList<Integer> arr) {
        Map<Integer, List<Integer>> colorIndices = new HashMap<>();
        
        // Populate the hashmap with color indices
        for (int i = 0; i < arr.size(); i++) {
            int color = arr.get(i);
            colorIndices.computeIfAbsent(color, k -> new ArrayList<>()).add(i);
        }
        
        int index = 0;
        
        // Traverse the hashmap in order (0, 1, 2) and update the input array
        for (int color = 0; color < 3; color++) {
            if (colorIndices.containsKey(color)) {
                for (int colorIndex : colorIndices.get(color)) {
                    arr.set(index, color);
                    index++;
                }
            }
        }
    }
}
Time taken: 2
