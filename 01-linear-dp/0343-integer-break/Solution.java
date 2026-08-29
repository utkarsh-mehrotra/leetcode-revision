/**
 * LeetCode 343. Integer Break
 * Approach: Mathematical -- the product-maximizing partition of n uses as
 * many 3s as possible, folding a remainder of 1 into the last 3 to form a
 * 4 (split as 2+2), since 3*3 > 2*2*2 for equal sums and 2*2 > 3*1.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public int integerBreak(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        int product = 1;
        while (n > 4) {
            product *= 3;
            n -= 3;
        }
        return product * n;
    }
}
