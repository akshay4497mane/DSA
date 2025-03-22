/*  
 * Problem: Find All Numbers Disappeared in an Array | Find all missing numbers in the range [1, n] from an unsorted array of size n.Input: nums = [4,3,2,7,8,2,3,1] Output: [5,6]  
 */

class Solution {
    /* Approach 1 : using HashMap
    */
    public List<Integer> findDisappearedNumbers_Approach1_HashMap(int[] A) {
        int n = A.length;
        Map<Integer, Boolean> m = new HashMap<>();
        for(int i=0; i<n; i++){
            m.put(A[i], true);
        }
        List<Integer> ans = new ArrayList<>();
        for(int i=1; i<=n; i++){
            if(!m.containsKey(i)) 
                ans.add(i);
        }
        return ans;
    }

    /* Approach 2 : using HashSet
        Time Complexity : O(N) Space Complexity : O(N)
    */
    public List<Integer> findDisappearedNumbers_Approach2_Set(int[] A) {
        int n = A.length;
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<n; i++){
            set.add(A[i]);
        }
        List<Integer> ans = new ArrayList<>();
        for(int i=1; i<=n; i++){
            if(!set.contains(i)) 
                ans.add(i);
        }
        return ans;
    }    
    /* Approach 2 : using in place modification of Array Values | for every ele -> find index ele-1 -> make it -ve ( A[ele-1] as negative ) 
        Time Complexity : O(N) Space Complexity : O(N)
    */
    public List<Integer> findDisappearedNumbers(int[] A) {
        int n=A.length;
        for(int i=0; i<n; i++){
            int correctIndex = Math.abs(A[i])-1; // range is 1-N | Also A[i] may be negative because of our modification
            A[correctIndex] = -Math.abs(A[correctIndex]);
        }
        List<Integer> ans = new LinkedList<>();
        for(int i=1; i<=n ; i++){
            if(A[i-1]>0) ans.add(i);
        }
        return ans;
    }
    /*  
     * Approach 4: Cycle Sort  
        * Time Complexity: O(n)  
        * - Each element is swapped at most once, leading to an O(n) cycle sort process.  
        * - The final pass to collect missing numbers is O(n), keeping the overall complexity O(n).  
        *  
        * Space Complexity: O(1)  
        * - Sorting is done in place without extra space (excluding output list).  
     * - Every element should be at index `A[i] - 1` if it's in the range [1, n].  
     * - Swap elements into correct positions.  
     * - The numbers that remain misplaced indicate missing numbers.  
     */
    public List<Integer> findDisappearedNumbers_Approach2(int[] A) {
        int i = 0, n = A.length;

        // Place each number at its correct index using cycle sort
        while (i < n) {
            int correctIndex = A[i] - 1; // Expected position for A[i]

            // Swap if the number is in range and not in its correct position
            if (A[i] > 0 && A[i] <= n && A[i] != A[correctIndex]) {
                swap(A, i, correctIndex);
            } else {
                i++; // Move to next element if no swap needed
            }
        }

        // Identify missing numbers
        List<Integer> ans = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            if (A[j] != j + 1) ans.add(j + 1); // If index+1 is missing, add to result
        }
        
        return ans;
    }

    // Swaps two elements in the array
    void swap(int[] A, int p, int q) {
        int temp = A[p];
        A[p] = A[q];
        A[q] = temp;
    }
}
