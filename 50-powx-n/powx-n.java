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
    public double myPow(double x, long n) {
        if (n == 0) return 1; // Base case
        if (n < 0) return 1 / myPow(x, -n); // Handle negative exponent

        if (n % 2 == 0) // If `n` is even, use x^n = (x^2)^(n/2)
            return myPow(x * x, n / 2);
        else // If `n` is odd, use x^n = x * (x^2)^((n-1)/2)
            return x * myPow(x * x, (n - 1) / 2);
    }
}
