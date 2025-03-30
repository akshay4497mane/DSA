/*
Problem: Implement a TwoSum data structure that allows adding numbers and finding if any two numbers sum up to a given value.

Sample Input/Output:
TwoSum obj = new TwoSum();
obj.add(1);
obj.add(3);
obj.add(5);
obj.find(4); // true (1+3)
obj.find(7); // true (3+4)
obj.find(10); // false

Approach: 
- Use a HashMap to store numbers and their frequencies.
- `add(n)`: Insert number and update its count.
- `find(value)`: Iterate through map, check if `value - key` exists.

Time Complexity: 
- `add(n)`: O(1) (HashMap insertion)
- `find(value)`: O(N) (Iterating through map)

Space Complexity: O(N) (Storing numbers in HashMap)
*/

class TwoSum {
    Map<Integer, Integer> map;
    
    public TwoSum() {
        map = new HashMap<>(); // Initialize HashMap to store numbers and their counts
    }
    
    public void add(int n) {
        // Add the number to map, increment count if exists, else initialize with 1
        map.put(n, 1 + map.getOrDefault(n, 0));
    }
    
    public boolean find(int value) {
        // Iterate through all stored numbers
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int toFind = value - entry.getKey(); // Calculate complement
            
            if (toFind == entry.getKey()) { // Special case: checking same number appearing twice
                if (map.get(toFind) > 1) return true; // It must appear at least twice
            } else {
                if (map.containsKey(toFind)) return true; // If complement exists, return true
            }
        }
        return false; // No valid pair found
    }
}
