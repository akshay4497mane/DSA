class Solution {
    public int calculate(String s) {
        if( s==null || s.isEmpty()) return 0;
        int N = s.length();
        Stack<Integer> st = new Stack<>();
        int currNum = 0;
        char currOp = '+';
        for(int i=0; i< N ; i++){
            char c = s.charAt(i);
            if(Character.isDigit(c))//number
                currNum = (currNum * 10) + (c - '0');
            if( !Character.isDigit(c) && !Character.isWhitespace(c) || i==N-1  ){//operator
                //i==N-1  this ensures last currNum, currOp is solved  
                if(currOp == '+')
                    st.push(currNum);
                else if(currOp == '-')
                    st.push(-currNum);
                else if(currOp == '*')
                    st.push( st.pop() * currNum );
                else if(currOp == '/')
                    st.push( st.pop() / currNum );
                currNum = 0;
                currOp = c;
            }
        }
        int ans = 0;
        while(!st.isEmpty()){
            ans += st.pop();
        }
        return ans;
    }
}