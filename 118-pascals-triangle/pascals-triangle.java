class Solution {
    public List<List<Integer>> generate(int numRows) {
        // List to store the generated Pascal's Triangle
        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        // Base case: First row with a single element 1
        ans.add(List.of(1));

        // Variable to track the length of the previous row
        int prevLen = 0;

        // Loop to generate the Pascal's Triangle rows
        for (int i = 1; i < numRows; i++) {
            // List to store the current row
            ArrayList<Integer> curr = new ArrayList<>();

            // Add the first element of each row (always 1)
            curr.add(1);

            // Calculate the length of the previous row
            prevLen = (i > 0) ? ans.get(i - 1).size() : 0;

            // Loop to calculate the elements in the current row based on the previous row
            for (int j = 0; j < prevLen; j++) {
                // Check if the next element in the current row is within bounds
                if (j + 1 < prevLen) {
                    // Calculate the next element using values from the previous row
                    curr.add(ans.get(i - 1).get(j) + ans.get(i - 1).get(j + 1));
                }
            }

            // Add the last element of each row (always 1)
            curr.add(1);

            // Add the newly generated row to the result
            ans.add(curr);
        }

        // Return the final Pascal's Triangle
        return ans;
    }
}