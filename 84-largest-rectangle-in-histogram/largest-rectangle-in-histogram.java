class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        //find left small boundary 
        int[] left = new int[n];
        Stack<Integer> st = new Stack<>();
        for(int i=0; i<n; i++){
            while(!st.isEmpty() && heights[st.peek()] >= heights[i] ){
                st.pop();
            }
            if(st.isEmpty()){
                left[i] = 0;
                st.push(i);
            }else{
                left[i] = st.peek()+1;    
                st.push(i);
            }
        }
    //find right small boundary
        int[] right = new int[n];
        st = new Stack<>();
        for(int i=n-1; i>=0; i--){
            while(!st.isEmpty() && heights[st.peek()] >= heights[i] ){
                st.pop();
            }
            if(st.isEmpty()){
                right[i] = n-1;
                st.push(i);
            }else{
                right[i] = st.peek()-1;    
                st.push(i);
            }
        }
        //Calculate Area = height * width(R-L+1)
        System.out.println("LEFT : ");
        for(int i=0; i<n; i++)
            System.out.print(left[i] + " ");
        System.out.println("\nRIGHT : ");
        for(int i=0; i<n; i++)
            System.out.print(right[i] + " ");
        System.out.println("\nMax Area : ");
        int ans = Integer.MIN_VALUE;
        for(int i=0; i<n; i++){
            int area = heights[i] * (right[i] - left[i] + 1);
            ans = Math.max(ans, area);
            System.out.print(area + " ");
        }
        return ans;
    }
}