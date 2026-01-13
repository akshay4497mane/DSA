/*
Revision 13 jan 2026

Create 2 HashMaps
1. key -> Node(freq, val)
2. Freq -> [key1,key2,key3]
1 -> 
2 -> akshay,  y,z
3 -> g
Maintain minFreq , capacity
Create Node class( key, value, freq)

*/
class LFUCache {
    Map<Integer, Node> cache = new HashMap<>(); //key -> Node(freq, val)  |1-1
    Map<Integer, LinkedHashSet<Integer>> freqMap = new HashMap<>(); //Freq -> [key1,key2, key3]
    int minFreq;
    int capacity;
    class Node{
        int key, val, freq;
        public Node(int key, int val, int freq){
            this.key = key;
            this.val = val;
            this.freq = freq;
        }
    }
    public LFUCache(int capacity) {
        this.capacity = capacity;
        minFreq = 0; // 0 or 1 or Integer.MIN_VALUE? 
    }
    public int get(int key) {
        if( !cache.containsKey(key) ) return -1;
        Node node = cache.get(key);
        updateFreq(node);
        return node.val;
    }
    void updateFreq( Node node ) {//Only when key exist | Dont call for insertion
        freqMap.get(node.freq).remove(node.key);
        if( node.freq == minFreq && freqMap.get(minFreq).isEmpty() ){
            minFreq++;
        }
        node.freq++;
        freqMap.computeIfAbsent(node.freq, (ignored) -> new LinkedHashSet<>()).add(node.key);
    }
    
    public void put(int key, int value) {
        if( cache.containsKey(key) ){
            Node node = cache.get(key);
            node.val = value;
            updateFreq(node);
        }else{
            if(capacity == cache.size()){
                int evictKey = freqMap.get(minFreq).iterator().next();
                cache.remove(evictKey);
                freqMap.get(minFreq).remove(evictKey);
            }
            Node node = new Node(key, value, 1);
            cache.put(key, node);
            freqMap.computeIfAbsent(1, (k)-> new LinkedHashSet<>() ).add(node.key);
            minFreq = 1;
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */


class LFUCache22 {
    // key: original key, value: frequency and original value.
    private Map<Integer, Pair<Integer, Integer>> cache;
    // key: frequency, value: All keys that have the same frequency.
    private Map<Integer, LinkedHashSet<Integer>> frequencies;
    private int minf;
    private int capacity;

    private void insert(int key, int frequency, int value) {
        cache.put(key, new Pair<>(frequency, value));
        frequencies.putIfAbsent(frequency, new LinkedHashSet<>());
        frequencies.get(frequency).add(key);
    }

    public LFUCache22(int capacity) {
        cache = new HashMap<>();
        frequencies = new HashMap<>();
        minf = 0;
        this.capacity = capacity;
    }

    public int get(int key) {
        Pair<Integer, Integer> frequencyAndValue = cache.get(key);
        if (frequencyAndValue == null) {
            return -1;
        }
        final int frequency = frequencyAndValue.getKey();
        final Set<Integer> keys = frequencies.get(frequency);
        keys.remove(key);
        if (keys.isEmpty()) {
            frequencies.remove(frequency);

            if (minf == frequency) {
                ++minf;
            }
        }
        final int value = frequencyAndValue.getValue();
        insert(key, frequency + 1, value);
        return value;
    }

    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }
        Pair<Integer, Integer> frequencyAndValue = cache.get(key);
        if (frequencyAndValue != null) {
            cache.put(key, new Pair<>(frequencyAndValue.getKey(), value));
            get(key);
            return;
        }
        if (capacity == cache.size()) {
            final Set<Integer> keys = frequencies.get(minf);
            final int keyToDelete = keys.iterator().next();
            cache.remove(keyToDelete);
            keys.remove(keyToDelete);
            if (keys.isEmpty()) {
                frequencies.remove(minf);
            }
        }
        minf = 1;
        insert(key, 1, value);
    }
}