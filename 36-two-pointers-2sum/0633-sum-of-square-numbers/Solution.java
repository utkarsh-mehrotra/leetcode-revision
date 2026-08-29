/**
 * LeetCode 633. Sum of Square Numbers
 * Approach: Two pointers converging over the implicit sorted range
 * [0, sqrt(c)] -- a is the low pointer, b starts at the high end. Since
 * a^2 increases and b^2 decreases monotonically as the pointers move,
 * this is exactly the Two Sum II pattern applied to squares instead of
 * array elements.
 * Time: O(sqrt(c)) | Space: O(1)
 */
class Solution {
    public boolean judgeSquareSum(int c) {
        long a = 0, b = (long) Math.sqrt(c);
        while (a <= b) {
            long sum = a * a + b * b;
            if (sum == c) return true;
            if (sum < c) a++;
            else b--;
        }
        return false;
    }
}
