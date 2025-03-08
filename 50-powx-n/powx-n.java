class Solution {
    /*    
    Approach 1: Recursive (O(n)) 
    - Recursively multiplies `x` `n` times.
    - Inefficient for large `n`.
    */
    public double myPow_Approach1(double x, long n) {
        if (n == 0) return 1; // Base case: x^0 = 1
        if (n < 0) return 1 / myPow_Approach1(x, -n); // Handle negative exponent
        return x * myPow_Approach1(x, n - 1); // Multiply x recursively
    }

    /*    
    Approach 2: Optimized (O(log n)) - Exponentiation by Squaring
    - Reduces recursive calls significantly by using `x^n = (x^2)^(n/2)`.
    */
    public double myPow_approach2(double x, long n) {
        if (n == 0) return 1; // Base case
        if (n < 0) return 1 / myPow(x, -n); // Handle negative exponent

        if (n % 2 == 0) // If `n` is even, use x^n = (x^2)^(n/2)
            return myPow(x * x, n / 2);
        else // If `n` is odd, use x^n = x * (x^2)^((n-1)/2)
            return x * myPow(x * x, (n - 1) / 2);
    }

    public double myPow(double x, long n) {
        if (n == 0) return 1; // Base case: x^0 = 1
        
        if (n < 0) { // Handle negative exponent
            n = -1 * n; // Convert to positive
            x = 1.0 / x; // Take reciprocal
        }
        
        double ans = 1; // Stores the result
        while (n > 0) {
            if (n % 2 == 1) { // If `n` is odd, multiply result by `x`
                ans = ans * x;
                n = n - 1;
            }
            x = x * x; // Square `x`
            n = n / 2; // Reduce `n` by half
        }
        return ans;
    }
}
