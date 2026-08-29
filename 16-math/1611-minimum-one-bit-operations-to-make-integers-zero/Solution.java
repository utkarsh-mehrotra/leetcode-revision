/**
 * LeetCode 1611. Minimum One Bit Operations to Make Integers Zero
 * Approach: Top-down recursion (no memo needed -- each call strictly
 * clears the highest bit, so the chain of calls never revisits a value).
 * If n's highest set bit is at position p, reaching 0 first requires
 * reaching the value 2^(p+1) - 1 (all lower bits set), which by the
 * reflected-Gray-code structure of this operation costs 2^(p+1) - 1
 * steps; from there, clearing bit p and solving the rest costs
 * minOps(n - 2^p) fewer steps, so the two combine by subtraction.
 * Time: O(log n) | Space: O(log n) recursion stack
 */
class Solution {
    public int minimumOneBitOperations(int n) {
        if (n == 0) return 0;
        int highestBit = Integer.highestOneBit(n);
        return (2 * highestBit - 1) - minimumOneBitOperations(n - highestBit);
    }
}
