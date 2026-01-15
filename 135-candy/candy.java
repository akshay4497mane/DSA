class Solution {
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
	    for(int b : bonus) {
	    	ansSum += b;
	    }
		return ansSum;        
    }
}