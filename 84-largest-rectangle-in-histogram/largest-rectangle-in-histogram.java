import java.util.Stack;

class Solution {
    /*
    Problem: Given an array representing the heights of histograms, find the area of the largest rectangle.
    
    Approach: Use stacks to find the nearest smaller element to the left and right for each bar.
    The width of the rectangle is determined by these boundaries.
    Intuition : We can form 1 rectangle for every height[i].
    To find area we need width. Thus for every i we need to traverse left until we find smaller bar than h[i]. and traverse right untill we find smaller bar than h[i].

    Time Complexity: O(n) - Each element is pushed and popped from the stack at most once.
    Space Complexity: O(n) - Extra space for left, right boundary arrays and stack.
    */
    
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        
        // Arrays to store the index of the nearest smaller element to the left and right
        int[] left = new int[n];
        int[] right = new int[n];
        Stack<Integer> st = new Stack<>();
        
        // Find nearest smaller element to the left
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                st.pop(); // Remove elements that are greater or equal
            }
            left[i] = (st.isEmpty()) ? 0 : st.peek() + 1; // Left boundary index
            st.push(i); // Push current index to stack
        }
        
        // Clear stack for the next computation
        st.clear();
        
        // Find nearest smaller element to the right
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                st.pop(); // Remove elements that are greater or equal
            }
            right[i] = (st.isEmpty()) ? n - 1 : st.peek() - 1; // Right boundary index
            st.push(i); // Push current index to stack
        }
        
        // Compute maximum rectangle area
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int width = right[i] - left[i] + 1; // Width of the rectangle
            int area = heights[i] * width; // Compute area
            maxArea = Math.max(maxArea, area); // Update max area if larger found
        }
        
        return maxArea; // Return largest rectangle area
    }
}
