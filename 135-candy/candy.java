/*
Approach 1 :
Time complexity : O(n^2). 
We need to traverse the array at most n times. This is because at most, a child will get n candies, and their candy count will be update once on each traversal.

Space complexity : O(n). One array, candies array of size n is used.
*/
class Solution_1 {
    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        boolean hasChanged = true;
        while (hasChanged) {
            hasChanged = false;
            for (int i = 0; i < ratings.length; i++) {
                if (
                    i != ratings.length - 1 &&
                    ratings[i] > ratings[i + 1] &&
                    candies[i] <= candies[i + 1]
                ) {
                    candies[i] = candies[i + 1] + 1;
                    hasChanged = true;
                }
                if (
                    i > 0 &&
                    ratings[i] > ratings[i - 1] &&
                    candies[i] <= candies[i - 1]
                ) {
                    candies[i] = candies[i - 1] + 1;
                    hasChanged = true;
                }
            }
        }
        int sum = 0;
        for (int candy : candies) {
            sum += candy;
        }
        return sum;
    }
}

/*
using 2 arrays
Time complexity : O(n). left2right and right2left arrays are traversed thrice.
Space complexity : O(n). Two arrays left2right and right2left of size n are used.
*/
class Solution_2 {
    public int candy(int[] ratings) {
        int sum = 0;
        int[] left2right = new int[ratings.length];
        int[] right2left = new int[ratings.length];
        Arrays.fill(left2right, 1);
        Arrays.fill(right2left, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left2right[i] = left2right[i - 1] + 1;
            }
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right2left[i] = right2left[i + 1] + 1;
            }
        }
        for (int i = 0; i < ratings.length; i++) {
            sum += Math.max(left2right[i], right2left[i]);
        }
        return sum;
    }
}

class Solution_3 {
/*
Approach 3: Using one array
2 passes 
        for loop L -> R | rating  ++
        for loop R -> L | rating  ++
Time complexity : O(n). The array candies of size n is traversed thrice.
Space complexity : O(n). An array candies of size n is used.        
*/
    public int candy(int[] ratings) {
	    int N = ratings.length;
	    int[] bonus = new int[N];
	    Arrays.fill(bonus, 1);
	    for(int i=1; i<N; i++) {
	    	if(ratings[i] > ratings[i-1]) {
	    		bonus[i] = bonus[i-1] +1;
	    	}
	    }
	    for(int i=N-2; i>=0 ; i--) {
	    	if(ratings[i] > ratings[i+1]) {
	    		bonus[i] = Math.max(bonus[i], bonus[i+1] + 1);
	    	}
	    }
	    int ansSum = 0;
	    for(int b : bonus)
	    	ansSum += b;
		return ansSum;        
    }
}
/*
Approach 4: Single Pass Approach with Constant Space
Time complexity : O(n). We traverse the rankings array once only.
Space complexity : O(1). Constant extra space is used.
*/
class Solution {
    public int count(int n) {
        return (n * (n + 1)) / 2;
    }

    public int candy(int[] ratings) {
        if (ratings.length <= 1) {
            return ratings.length;
        }
        int candies = 0;
        int up = 0;
        int down = 0;
        int oldSlope = 0;
        for (int i = 1; i < ratings.length; i++) {
            int newSlope = (ratings[i] > ratings[i - 1])
                ? 1
                : (ratings[i] < ratings[i - 1] ? -1 : 0);

            if ( (oldSlope > 0 && newSlope == 0) || (oldSlope < 0 && newSlope >= 0) ) {
                candies += count(up) + count(down) + Math.max(up, down);
                up = 0;
                down = 0;
            }
            if (newSlope > 0) {
                up++;
            } else if (newSlope < 0) {
                down++;
            } else {
                candies++;
            }
            oldSlope = newSlope;
        }
        candies += count(up) + count(down) + Math.max(up, down) + 1;
        return candies;
    }
}