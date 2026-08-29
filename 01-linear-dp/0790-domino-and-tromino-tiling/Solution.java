/**
 * LeetCode 790. Domino and Tromino Tiling
 * Approach: Top-down memoized recursion on the known recurrence
 * f(n) = 2*f(n-1) + f(n-3), derived from casework on how the rightmost
 * column(s) of a 2 x n board are covered.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private Long[] memo;

    public int numTilings(int n) {
        memo = new Long[n + 1];
        return (int) f(n);
    }

    private long f(int n) {
        if (n < 0) return 0;
        if (n == 0) return 1;
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (memo[n] != null) return memo[n];
        long result = (2 * f(n - 1) % MOD + f(n - 3)) % MOD;
        memo[n] = result;
        return result;
    }
}
