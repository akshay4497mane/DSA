class Solution {
    /**
     * Finds the minimum speed at which Koko can eat all bananas within the given hours.
     *
     * @param piles         Array representing the number of bananas in each pile.
     * @param hoursAvailable The maximum hours available to finish eating.
     * @return Minimum eating speed required to finish eating all bananas within the given time.
     */
    public int minEatingSpeed(int[] piles, int hoursAvailable) {
        // Find the maximum bananas in a single pile to define the search range
        int maxPile = Arrays.stream(piles).max().orElse(0);

        // Binary search to find the minimum eating speed
        int left = 1; // Minimum possible speed
        int right = maxPile; // Maximum possible speed
        int optimalSpeed = maxPile;

        while (left < right) {
            int midSpeed = left + (right - left) / 2; // Calculate the middle speed

            if (canEatAllBananas(midSpeed, piles, hoursAvailable)) {
                // If possible to eat all bananas with current speed, try for a smaller speed
                optimalSpeed = midSpeed;
                right = midSpeed ;
            } else {
                // Otherwise, increase the speed
                left = midSpeed + 1;
            }
        }
        return optimalSpeed;
    }
    /**
     * Checks if Koko can eat all bananas at the given eating speed within the available hours.
     *
     * @param eatingSpeed    Current eating speed to test.
     * @param piles          Array representing the number of bananas in each pile.
     * @param hoursAvailable The maximum hours available to finish eating.
     * @return True if it's possible to eat all bananas within the given time at this speed, false otherwise.
     */
    public boolean canEatAllBananas(int eatingSpeed, int[] piles, int hoursAvailable) {
        int hoursNeeded = 0;

        for (int bananas : piles) {
            // Calculate hours needed for the current pile at the given speed
            hoursNeeded += (bananas + eatingSpeed - 1) / eatingSpeed; 
            // This formula rounds up the division to account for any leftover bananas requiring an extra hour.
            // Equivalent to Math.ceil(bananas / eatingSpeed)
        }

        // Check if the total hours required is within the available time
        return hoursNeeded <= hoursAvailable;
    }
}