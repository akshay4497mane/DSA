class Solution { //Sort an array of colors using COUNTING SORT
    // Time Complexity: O(N), Space Complexity: O(numColors)
    public void sortColors(int[] A) {
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