class Solution {
    public int[] dailyTemperatures_stack(int[] t) {
        int n=t.length;
        int[] ans=new int[n];
        Deque<Integer> st=new ArrayDeque<>();
        for(int i=0;i<n;i++){
            while(!st.isEmpty() && t[i]>t[st.peek()]){
                int idx=st.pop();
                ans[idx]=i-idx;
            }
            st.push(i);
        }
        return ans;
    }
    //DP
    public int[] dailyTemperatures(int[] t) {
        int n=t.length;
        int[] ans=new int[n];
        for(int i=n-2;i>=0;i--){
            int j=i+1;
            while(j<n && t[j]<=t[i]){
                if(ans[j]==0){ j=n; break; }
                j=j+ans[j];
            }
            if(j<n) ans[i]=j-i;
        }
        return ans;
    }
}