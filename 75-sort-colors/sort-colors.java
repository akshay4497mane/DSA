class Solution {
    // Function to sort an array of colors in-place using DUTCH NATIONAL FLAG ALGORITHM
    public void sortColors(int[] A) {// Time Complexity: O(N), Space Complexity: O(1)
        int N = A.length, p1 = 0, p2 = N - 1, i = 0;
        if (N <= 1)
            return;
        while (i <= p2) {
            if (A[i] == 0) {
                swap(A, i, p1);
                i++;
                p1++;
            } else if (A[i] == 2) {
                swap(A, i, p2);
                p2--; //not incrementing i
            } else { // A[i] == 1
                i++;
            }
        }
    }
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //Sort an array of colors using COUNTING SORT
    // Time Complexity: O(N), Space Complexity: O(numColors)
    public void sortColors2PassCountingSort(int[] A) {
        int numColors = 3; // Number of colors in the input array
        int[] count = new int[numColors];
        int N = A.length; // Length of the input array
        
        for (int i = 0; i < N; i++)
            count[A[i]]++; // Count the occurrences of each color

        int base = 0; // Index to update the sorted array
        for (int x = 0; x < numColors; x++) {// Update the array with sorted colors based on the count
            for (int k = 0; k < count[x]; k++)
                A[base + k] = x;
            base += count[x];
        }
    }
}