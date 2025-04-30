/**
 * Problem Statement:
 * Implement an LRU (Least Recently Used) Cache with a given capacity.
 * The cache should support two operations:
 * 1. get(key) - Retrieve the value of the key if present, else return -1.
 * 2. put(key, value) - Insert or update the key-value pair. 
 *    If the cache reaches capacity, remove the least recently used item.
 * 
 * Solution Approach:
 * - Use a HashMap for O(1) access to nodes.
 * - Use a Doubly Linked List (DLL) to maintain the order of usage.
 * - The least recently used (LRU) item will always be at the head.
 * - The most recently used (MRU) item will always be at the tail.
 * - When an item is accessed, move it to the tail (MRU position).
 * - When an item is added, insert it at the tail.
 * - When the cache is full, remove the item from the head (LRU position).
 * 
 * Time Complexity:
 * - get(key) -> O(1)
 * - put(key, value) -> O(1)
 * 
 * Space Complexity:
 * - O(capacity) for storing key-value pairs.
 */

class LRUCache_1 {
    int capacity;
    Map<Integer, DLLNode> map; // HashMap to store key-node pairs for O(1) access
    DLLNode head, tail; // Dummy head and tail nodes for easier operations

    /**
     * Doubly Linked List (DLL) Node
     */
    private class DLLNode {
        Integer key, val;
        DLLNode prev, next;
        DLLNode(int key, int val, DLLNode prev, DLLNode next) {
            this.key = key;
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * Initialize the LRUCache with a given capacity.
     */
    public LRUCache_1(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        
        // Create dummy head and tail nodes to simplify operations
        head = new DLLNode(-1, -1, null, null);
        tail = new DLLNode(-1, -1, head, null);
        head.next = tail;
        tail.prev = head;
    }

    /**
     * Get the value of the key if it exists in the cache.
     * If found, move it to the tail (MRU position) and return its value.
     * If not found, return -1.
     */
    public int get(int key) {
        if (map.containsKey(key)) {
            DLLNode node = map.get(key);
            removeFromDLL(node); // Remove from current position
            addToTailDLL(node);  // Move to the most recently used position
            return node.val;
        }
        return -1; // Key not found
    }

    /**
     * Put a key-value pair in the cache.
     * If the key already exists, update its value and move it to the tail.
     * If the key is new and capacity is full, remove the least recently used item.
     */
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            removeFromDLL(map.get(key)); // Remove existing node
        }

        DLLNode node = new DLLNode(key, value, null, null);
        map.put(key, node); // Insert into HashMap
        addToTailDLL(node); // Move to the most recently used position

        if (map.size() > capacity) { // If cache exceeds capacity
            map.remove(head.next.key); // Remove from HashMap
            removeFromDLL(head.next); // Remove from DLL (least recently used)
        }
    }

    /**
     * Add a node to the tail of the doubly linked list (most recently used).
     */
    void addToTailDLL(DLLNode node) {
        tail.prev.next = node;
        node.prev = tail.prev;
        node.next = tail;
        tail.prev = node;
    }

    /**
     * Remove a node from its current position in the doubly linked list.
     */
    void removeFromDLL(DLLNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}


//APPROACH 2 : Using JAVA LinkedHashMap
class LRUCache {
    int capacity;  
    LinkedHashMap<Integer, Integer> dic; // Maintains insertion order and allows LRU eviction

    public LRUCache(int capacity) {
        this.capacity = capacity;
        dic = new LinkedHashMap<>(5, 0.75f, true) { 
            // 5 -> Initial capacity (not related to LRU, just a starting size)
            // 0.75f -> Load factor (controls resizing, 0.75 means resize at 75% full)
            // true -> Access-order mode (moves recently accessed keys to the end)
            
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                // eldest -> The oldest entry (first inserted or least recently accessed)
                // Automatically removes the eldest entry if size exceeds capacity
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        return dic.getOrDefault(key, -1); // Returns value if exists, else -1
    }

    public void put(int key, int value) {
        dic.put(key, value); // Inserts key-value pair, moves key to end if exists
    }
}

/**
 * Example Usage:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key, value);
 */
