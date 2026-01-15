/*
Note : Approach 3 is sufficient and cleaner.

15 JAN 2026 : read revision.

Your Approach 3 is the interview-grade solution.

What you should say in interview (verbatim safe)
“I can do this with a stack + heap using lazy deletion, but it degrades to O(n) in worst case. A cleaner solution is using two balanced BSTs with unique IDs, which guarantees log-n for all operations.”

That sentence alone signals seniority.

What to memorise (strict)

Approach 3 only (TreeSet / TreeMap + Node)

Why index/id is needed

Why comparator defines uniqueness

Why PriorityQueue fails for delete-arbitrary


Approach1: Two Balanced Trees/2 TreeSets ( BST - Red Black Tree / ) [guarantees No duplicates, Ordering]
push() O(log n)
pop() O(log n)
popMax() O(log n)
top() O(1)
peekMax() O(1)
TreeSet gives you:
1.O(log n) insert/delete
2.last() → max element
3.pollLast() → remove max
4.ability to remove arbitrary element (unlike PriorityQueue)

PriorityQueue alone fails because:
-removing a middle element is O(n)
-can’t sync stack + max efficiently
*/
class MaxStack_1 {
    private TreeSet<int[]> stack; // Stack -> Give last element | Ordering index 
    private TreeSet<int[]> values; //Heap -> Give Max element | ordering value
    private int index;
    public MaxStack_1() {
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
/*
Approach 2: Stack + Heap + Lazy Deletion
Stack / Heap | removed set + index 
push() O(log n)
pop() Amortized O(log n), Worst O(n)
popMax() Amortized O(log n), Worst O(n)

top() Amortized O(1), Worst O(n)
peekMax() Amortized O(log n), Worst O(n)
*/
class MaxStack_2 {
    private Stack<int[]> stack;
    private Queue<int[]> heap;
    private Set<Integer> removed;
    private int index;
    public MaxStack_2() {
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

/*
Approach 3 : USE approach 1 -> dont use int[] -> use NODE

Final blunt takeaway (interview-ready)

TreeSet is required for ordered removal + arbitrary delete

index exists only to break duplicates deterministically

comparator defines uniqueness

using int[] is a bad practice in interviews

always model domain with a class when logic matters
*/

class MaxStack {
    private static class Node {
        int value;
        int id;
        Node(int value, int id) {
            this.value = value;
            this.id = id;
        }
    }
    private TreeSet<Node> stackByOrder; // behaves like stack (top = latest id)
    private TreeSet<Node> stackByValue; // behaves like max-heap (max value, latest id wins)
    private int nextId = 0;
    public MaxStack() {
        stackByOrder = new TreeSet<>( (a, b) -> a.id != b.id ? a.id - b.id : a.value - b.value );

        stackByValue = new TreeSet<>(
            (a, b) -> { if (a.value != b.value) 
                            return a.value - b.value;
                        return a.id - b.id;
                        }
                );
    }

    public void push(int value) {
        Node node = new Node(value, nextId++);
        stackByOrder.add(node);
        stackByValue.add(node);
    }

    public int pop() {
        Node node = stackByOrder.pollLast();
        stackByValue.remove(node);
        return node.value;
    }

    public int top() {
        return stackByOrder.last().value;
    }

    public int peekMax() {
        return stackByValue.last().value;
    }

    public int popMax() {
        Node node = stackByValue.pollLast();
        stackByOrder.remove(node);
        return node.value;
    }
}












