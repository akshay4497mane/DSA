import java.util.PriorityQueue;

/*
PROBLEM STATEMENT:
There are 3 kinds of queries on a stream of numbers:
(1, X) - Add X in your list of numbers
(2, X) - Add X to all the existing numbers
(3)    - Print the min number so far and remove it from the list

EXAMPLE:
(1, 3) - Add 3 to the list: [3]
(1, 5) - Add 5 to the list: [3, 5]
(2, 10) - Add 10 to all the existing numbers: [13, 15]
(1, 12) - Add 12 to the list: [12, 13, 15]
(3)    - Find and remove the minimum: Print 12
*/

public class StreamProcessor_MinHeap {
  // Min-heap to efficiently track the smallest number
  private PriorityQueue<Integer> minHeap;
  // Tracks the cumulative offset added to all numbers
  private int addOffset;

  // Constructor to initialize the min-heap and offset
  public StreamProcessor_MinHeap() {
    minHeap = new PriorityQueue<>();
    addOffset = 0;
  }

  /**
   * (1, X) - Adds X to the list of numbers.
   * @param value The value to be added.
   * Complexity: O(log N) where N is the number of elements in the heap.
   */
  public void query1_addNo(Integer value) {
    // Adjust the value by subtracting the cumulative offset before adding to the heap
    minHeap.add(value - addOffset);
  }

  /**
   * (2, X) - Adds X to all existing numbers in the list.
   * @param X The value to be added to all numbers.
   * Complexity: O(1) (Constant time operation).
   */
  public void query2_addXAll(Integer X) {
    // Increment the cumulative offset, affecting all future queries
    addOffset += X;
  }

  /**
   * (3) - Finds and removes the minimum number from the list.
   * @return The minimum number after applying the offset, or -1 if the list is empty.
   * Complexity: O(log N) for heap extraction.
   */
  public int query3_findMin() {
    // Check if the heap is empty
    if (minHeap.isEmpty()) {
      return -1; // Return -1 if no numbers are available
    }
    // Adjust the minimum value by adding back the offset and remove it from the heap
    return minHeap.remove() + addOffset;
  }

  public static void main(String[] args) {
    // Example usage of StreamProcessor
    StreamProcessor_MinHeap sp = new StreamProcessor_MinHeap();

    // (1, 3) - Add 3
    sp.query1_addNo(3); // List: [3]

    // (1, 5) - Add 5
    sp.query1_addNo(5); // List: [3, 5]

    // (2, 10) - Add 10 to all numbers
    sp.query2_addXAll(10); // List: [13, 15]

    // (1, 12) - Add 12
    sp.query1_addNo(12); // List: [12, 13, 15]

    // (3) - Find and remove the minimum
    System.out.println(sp.query3_findMin()); // Output: 12
  }
}
