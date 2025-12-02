//Approach1: Two Balanced Trees/2 TreeSets ( BST - Red Black Tree / ) [guarantees No duplicates, Ordering]
class MaxStack {
    private TreeSet<int[]> stack; // Stack -> Give last element | Ordering index 
    private TreeSet<int[]> values; //Heap -> Give Max element | ordering value
    private int index;
    public MaxStack() {
        Comparator<int[]> comp = (a, b) -> { return a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]; };
        stack = new TreeSet<>(comp);
        values = new TreeSet<>(comp);
        index = 0;
    }
    public void push(int value) {
        stack.add(new int[] { index, value }); //stack
        values.add(new int[] { value, index }); //heap
        index++;  
    }
    public int pop() {
        int[] pair = stack.pollLast();
        values.remove(new int[] { pair[1], pair[0] });
        return pair[1];
    }
    public int top() {
        return stack.last()[1];
    }
    public int peekMax() {
        return values.last()[0];
    }
    public int popMax() {
        int[] pair = values.pollLast();
        stack.remove(new int[] { pair[1], pair[0] });
        return pair[0];
    }
}
/* Approach 2 : Stack & Heap | Lazy Removed | 
Stack / Heap | removed set + index 
 */
class MaxStack_1 {
    private Stack<int[]> stack;
    private Queue<int[]> heap;
    private Set<Integer> removed;
    private int index;

    public MaxStack_1() {
        stack = new Stack<>();
        heap = new PriorityQueue<>((a, b) -> b[0] - a[0] == 0 ? b[1] - a[1] : b[0] - a[0]);
        removed = new HashSet<>();
    }

    public void push(int x) {
        stack.add(new int[] { x, index });
        heap.add(new int[] { x, index });
        index++;
    }

    public int pop() {
        while (removed.contains(stack.peek()[1])) {
            stack.pop();
        }
        int[] top = stack.pop();
        removed.add(top[1]);
        return top[0];
    }

    public int top() {
        while (removed.contains(stack.peek()[1])) {
            stack.pop();
        }
        return stack.peek()[0];
    }

    public int peekMax() {
        while (removed.contains(heap.peek()[1])) {
            heap.poll();
        }
        return heap.peek()[0];

    }

    public int popMax() {
        while (removed.contains(heap.peek()[1])) {
            heap.poll();
        }
        int[] top = heap.poll();
        removed.add(top[1]);
        return top[0];
    }
}