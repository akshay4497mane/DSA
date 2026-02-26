class Solution {

    public String minRemoveToMakeValid(String s) {
        StringBuilder firstPass = new StringBuilder();
        int open = 0;
        // Pass 1: remove extra ')'
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                open++;
                firstPass.append(ch);
            }
            else if (ch == ')') {
                if (open > 0) {
                    open--;                // match one '('
                    firstPass.append(ch);
                }
                // else skip invalid ')'
            }
            else {
                firstPass.append(ch);
            }
        }
        // Pass 2: remove extra '(' from right
        StringBuilder result = new StringBuilder();
        int close = 0;

        for (int i = firstPass.length() - 1; i >= 0; i--) {

            char ch = firstPass.charAt(i);

            if (ch == ')') {
                close++;
                result.append(ch);
            }
            else if (ch == '(') {

                if (close > 0) {
                    close--;              // match one ')'
                    result.append(ch);
                }
                // else skip extra '('
            }
            else {
                result.append(ch);
            }
        }
        return result.reverse().toString();
    }    

    public String minRemoveToMakeValid_old(String s) {
        Stack<Character> st = new Stack<>();
        int removeCount = 0;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length(); i++){
            Character ch = s.charAt(i);
            if(ch == '('){
                st.push(ch); sb.append(ch);
            }
            else if( ch==')' ){
                if( !st.isEmpty() ){
                    st.pop(); sb.append(ch);
                }else{
                    removeCount++;
                }
            }else{
                sb.append(ch);
            }
        }
        removeCount += st.size();
        return sb.toString();
    }
}