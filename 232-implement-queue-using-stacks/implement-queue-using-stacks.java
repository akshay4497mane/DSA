class MyQueue {
    Stack<Integer> inSt;
    Stack<Integer> outSt;
    public MyQueue() {
        inSt = new Stack<>();
        outSt = new Stack<>();
    }
    public void push(int x) {
        inSt.push(x);
    }
    public int pop() {
        if( outSt.isEmpty() )
            while(!inSt.isEmpty()){
                outSt.push( inSt.pop() );
            }
        return outSt.pop();
    }    
    public int peek() {
        if( outSt.isEmpty() )
            while(!inSt.isEmpty()){
                outSt.push( inSt.pop() );
            }
        return outSt.peek();        
    }    
    public boolean empty() {
        return outSt.isEmpty() && inSt.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */