/* Problem: Find the median of two sorted arrays of different sizes.
   Time Complexity: O((m+n)/2) -> Iterates until the median index is reached.
   Space Complexity: O(1) -> Uses only constant extra space. */

class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        m = A.length;
        n = B.length;
        
        // If total number of elements is even, find two middle values and return their average
        if ((m + n) % 2 == 0) {
            for (int i = 0; i < (m + n) / 2 - 1; i++) {
                getMinimumAndMoveNext(A, B); // Discard elements until we reach median positions
            }
            return (double) (getMinimumAndMoveNext(A, B) + getMinimumAndMoveNext(A, B)) / 2;
        } else { 
            // If total number of elements is odd, return the middle value
            for (int i = 0; i < (m + n) / 2; i++) {
                getMinimumAndMoveNext(A, B); // Discard elements until we reach the median
            }
            return getMinimumAndMoveNext(A, B);
        }
    }
    
    int p1 = 0, p2 = 0; // Pointers for arrays A and B
    int m = 0, n = 0;   // Lengths of A and B
    
    /* Returns the smaller element between A[p1] and B[p2] and moves the corresponding pointer */
    private int getMinimumAndMoveNext(int[] A, int[] B) {
        if (p1 < m && p2 < n) {
            return A[p1] <= B[p2] ? A[p1++] : B[p2++];
        } else if (p1 < m) {
            return A[p1++]; // If B is exhausted, pick from A
        } else if (p2 < n) {
            return B[p2++]; // If A is exhausted, pick from B
        }
        return -1; // This case should never occur in a valid input
    }

    /* Stupid Attempt: Incorrect approach - Miscalculates median index and does not track elements properly */
    public double findMedianSortedArrays_BadAttempt_29_2096passed(int[] A, int[] B) {
        int m = A.length, n = B.length;
        int p1 = 0, p2 = 0, i = 0;
        
        // Incorrectly assumes that simply iterating and comparing elements will reach the median
        while (p1 < m && p2 < n) {
            if (A[p1] <= B[p2]) {
                p1++;
            } else {
                p2++;
            }
            i++;
            
            System.out.println(p1 + " " + p2 + " " + i + " " + ((m + n - 1) / 2));
            
            if (i == ((m + n - 1) / 2)) { // Attempting to find median index incorrectly
                if ((m + n) % 2 == 0) { // Even case
                    int num1 = (A[p1] <= B[p2]) ? A[p1++] : B[p2++];
                    int num2 = 0;
                    if (p1 < m) {
                        if (p2 < n) {
                            num2 = (A[p1] <= B[p2]) ? A[p1++] : B[p2++];
                        } else {
                            num2 = A[p1++];
                        }
                    } else {
                        num2 = B[p2++];
                    }
                    return ((double) num1 + num2) / 2;
                } else { // Odd case
                    return (A[p1] <= B[p2]) ? A[p1++] : B[p2++];
                }
            }
        }
        
        // Fails to handle cases where one array is exhausted too early
        while (p1 < m) {
            p1++;
            i++;
            if (i == ((m + n - 1) / 2)) {
                if ((m + n) % 2 == 0) {
                    int num1 = A[p1++];
                    int num2 = A[p1++];
                    return ((double) num1 + num2) / 2;
                } else {
                    return A[p1++];
                }
            }
        }
        
        while (p2 < n) {
            p2++;
            i++;
            if (i == ((m + n - 1) / 2)) {
                if ((m + n) % 2 == 0) {
                    int num1 = B[p2++];
                    int num2 = B[p2++];
                    return ((double) num1 + num2) / 2;
                } else {
                    return B[p2++];
                }
            }
        }
        
        return -1; // Incorrect fallback return
    }
}
