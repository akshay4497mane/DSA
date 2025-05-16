class Solution {
    public String simplifyPath(String path) {
        int i=0;
        Stack<String> st = new Stack<>();
        StringBuilder curr = new StringBuilder();
        while(i<=path.length()){
            if(i==path.length() || path.charAt(i)=='/'){
                i++;
                String s = curr.toString();
                curr.setLength(0);
                System.out.println("Found : " + s);
                if(s.equals(".") || s.isEmpty()){
                    continue;
                }else if(s.equals("..")){
                    if(!st.isEmpty()){
                        st.pop();
                    }
                }else{
                    st.push(s);
                }
            }else{
                curr.append(path.charAt(i++));
            }
        }
        StringBuilder ans = new StringBuilder();
        for(String str : st){
            ans.append("/");
            ans.append(str);
        }
        if(ans.length()==0) return "/";
        return ans.toString();
    }
}