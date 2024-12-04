class Solution {
    /**
     * Finds the minimum ship capacity required to ship all packages within the given days.
     * @param weights Array representing the weights of packages.
     * @param days Number of days within which all packages must be shipped.
     * @return The minimum ship capacity required.
     
Time Complexity : \U0001d442( N * log(sum(weights)) )
Space Complexity : O(1)
*/
    public int shipWithinDays(int[] weights, int days) {
        // Minimum possible capacity is the heaviest package
        int minCapacity = Arrays.stream(weights).max().getAsInt();
        // Maximum possible capacity is the sum of all package weights
        int maxCapacity = Arrays.stream(weights).sum();
        int optimalCapacity = maxCapacity; // Default to the largest possible capacity

        // Binary search to find the minimum capacity
        while (minCapacity <= maxCapacity) {
            int midCapacity = minCapacity + (maxCapacity - minCapacity) / 2;

            // Check if midCapacity can ship all packages within the given days
            if (canShipWithCapacity(midCapacity, weights, days)) {
                optimalCapacity = midCapacity; // Update the result to a smaller feasible capacity
                maxCapacity = midCapacity - 1; // Try for a smaller capacity
            } else {
                minCapacity = midCapacity + 1; // Increase capacity to meet constraints
            }
        }
        return optimalCapacity;
    }

    /**
     * Determines if a given ship capacity can handle all packages within the specified days.
     * @param capacity Ship capacity to test.
     * @param weights Array representing the weights of packages.
     * @param days Number of days available for shipping.
     * @return true if the capacity is sufficient; false otherwise.
     */
    private boolean canShipWithCapacity(int capacity, int[] weights, int days) {
        int daysRequired = 1; // Start with the first day
        int currentLoad = 0;  // Current weight on the ship

        for (int weight : weights) {
            // If adding the current package exceeds the capacity, start a new day
            if (currentLoad + weight > capacity) {
                daysRequired++;
                currentLoad = weight; // Start new day's load with this package
            } else {
                currentLoad += weight; // Add package to the current day's load
            }

            // If days required exceeds the allowed days, return false
            if (daysRequired > days) {
                return false;
            }
        }

        return true; // Capacity is sufficient for the given days
    }
}
