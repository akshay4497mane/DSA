class Solution2 {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for(char ch : s.toCharArray()){
            if(ch == '(' || ch=='{'||ch=='[')
                st.push(ch);
            else
                if(!st.isEmpty() &&
                    ( st.peek()=='(' && ch == ')' ||
                      st.peek()=='{' && ch == '}' ||
                       st.peek()=='[' && ch == ']')
                  )
                    st.pop();
                else
                    return false;
        }
        return st.isEmpty();
    }
}

class Solution {
    public boolean isValid(String s) {
        HashMap<Character,Character> maps=new HashMap<Character,Character>();
        maps.put(')','(');
        maps.put(']','[');
        maps.put('}','{');
        Stack<Character> stack=new Stack<Character>();
        for(int i=0;i<s.length();i++){
            char c=s.charAt(i);
            if(maps.containsKey(c)){
                if(stack.empty()||stack.pop()!=maps.get(c))
                    return false;
            }
            else
                stack.push(c);
        }
        return stack.empty();
    }
}