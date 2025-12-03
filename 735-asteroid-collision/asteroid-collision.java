class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();
        for(int a : asteroids){
            boolean isAalive = true;
            while( isAalive && !st.isEmpty() && st.peek()>0 && a<0 ){
                int top = st.peek();
                if( top > Math.abs(a) ){ //destroy A
                    isAalive = false;
                }else if(top < Math.abs(a)){
                    st.pop();
                }else{ // top == A  => destroy both
                    isAalive = false;
                    st.pop();
                }
            }
            if(isAalive) st.push(a);
        }
        int[] ans = new int[st.size()];
        //return st.stream().mapToInt(Integer::intValue).toArray();
        for (int i = 0; i < ans.length; i++) 
            ans[i] = st.get(i);
        /*
        for(int i=ans.length-1; i>=0; i--){
            ans[i] = st.pop();
        }
        */
        return ans;
    }
}

