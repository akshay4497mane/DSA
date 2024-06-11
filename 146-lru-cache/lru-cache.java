//APPROACH 2 : Using HashMap + DLL
class LRUCache {
    class DLLNode{
        int key, val;
        DLLNode next, prev;
        DLLNode(int k, int v, DLLNode n, DLLNode p){
            key = k; val=v; next = n; prev = p;
        }
    }
    int capacity;
    Map<Integer, DLLNode> m;
    DLLNode head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        m = new HashMap<>();
        head = new DLLNode(-1, -1, null, null);
        tail = new DLLNode(-1, -1, null, null);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if( m.containsKey(key) ){
            DLLNode node = m.get(key);
            remove(node);
            add(node);
            return node.val;           
        } 
        return -1;
    }
    
    public void put(int key, int val) {
        if( m.containsKey(key) ){
            DLLNode oldNode = m.get(key);
            remove(oldNode);
            //m.remove(key);
        }
        DLLNode node = new DLLNode(key, val, null, null);
        m.put(key, node);
        add(node);

        if( m.size() > capacity ){
            m.remove( head.next.key );
            remove(head.next);
        }
    }
    void add(DLLNode node){ //add in TAIL
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;
    }
    void remove(DLLNode node){ //remove from HEAD
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}


//APPROACH 1 : Using inbuilt LinkedHashMap of Java
class LRUCache_1 {
    int capacity;
    LinkedHashMap<Integer, Integer> lhm;
    public LRUCache_1(int capacity) {
        this.capacity = capacity;
        lhm = new LinkedHashMap<>(5, 0.75f, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest){
                return size() > capacity;
            }
        };
    }
    
    public int get(int key) {
        return lhm.getOrDefault(key, -1);
    }
    
    public void put(int key, int val) {
        lhm.put(key, val);        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,val);
 */