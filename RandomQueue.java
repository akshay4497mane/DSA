/*
Problem: Design a Queue that supports normal operations and also a getRandom() operation.
Required operations:
enqueue(x) → insert element at rear
dequeue() → remove element from front
getRandom() → return any random element currently present in queue

Constraint: All operations should be O(1)

Example: Queue state evolution
enqueue(10)
enqueue(20)
enqueue(30)
enqueue(40)
Queue = [10,20,30,40]
getRandom() → could return 10 / 20 / 30 / 40
dequeue() → removes 10
Queue = [20,30,40]
getRandom() → could return 20 / 30 / 40

Key observation
LinkedList queue does not support O(1) random access.
ArrayList supports O(1) indexing → ideal for random selection.

But removing from front in ArrayList is O(n).
Solution → maintain a head pointer instead of removing elements physically.

Data Structures
ArrayList<Integer> list → stores elements
int head → index of current front
Random random → random generator
Queue size = list.size() - head

Approach
enqueue(x)
1 add element at end of ArrayList
dequeue()
1 read element at index head
2 increment head pointer
3 occasionally cleanup memory if head grows too large
getRandom()
1 generate random index between
head → list.size()-1
2 return element at that index

Algorithm
enqueue(x)
add x to list
dequeue()
if head >= list.size → queue empty
value = list.get(head)
head++
return value
getRandom()
randomIndex = head + random(list.size() - head)
return list.get(randomIndex)

Time Complexity:
enqueue → O(1)
dequeue → O(1) amortized
getRandom → O(1)

Space Complexity: O(n)

Interview explanation (very important)
Why not LinkedList?
Random access O(n)

Why ArrayList works?
Random access O(1)

Why head pointer?
Avoid O(n) shift during dequeue

Why periodic cleanup?
Prevent unused elements occupying memory

One-line intuition
Treat queue as sliding window over ArrayList and pick random index inside window.
*/
import java.util.*;
class Main {
    public static void main(String[] args) {
        RandomQueue q = new RandomQueue();

        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.enqueue(40);

        System.out.println(q.getRandom());
        System.out.println(q.getRandom());

        System.out.println(q.dequeue());
        System.out.println(q.getRandom());

        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
    }
}

class RandomQueue {
    private List<Integer> list;
    private int head;
    private Random random;
    public RandomQueue() {
        list = new ArrayList<>();
        head = 0;
        random = new Random();
    }

    // enqueue element
    public void enqueue(int val) {
        list.add(val);
    }

    // dequeue element
    public int dequeue() {
        if (isEmpty()) throw new RuntimeException("Queue empty");
        int val = list.get(head);
        head++;
        // memory cleanup occasionally
        if (head > 100 && head > list.size() / 2) {
            list = new ArrayList<>(list.subList(head, list.size()));
            head = 0;
        }
        return val;
    }

    // get random element
    public int getRandom() {
        if (isEmpty()) throw new RuntimeException("Queue empty");
        int index = head + random.nextInt(list.size() - head);
        return list.get(index);
    }

    public boolean isEmpty() {
        return head >= list.size();
    }

    public int size() {
        return list.size() - head;
    }
}
