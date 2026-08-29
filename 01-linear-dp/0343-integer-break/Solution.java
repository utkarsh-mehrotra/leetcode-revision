/**
 * LeetCode 343. Integer Break
 * Approach: Top-down memoized recursion -- breakInt(n) tries every first
 * piece i and takes the best of leaving the rest whole (i*(n-i)) or
 * breaking it further (i*breakInt(n-i)).
 * Time: O(n^2) | Space: O(n)
 */
class Solution {
    private Integer[] memo;

    public int integerBreak(int n) {
        memo = new Integer[n + 1];
        return solve(n);
    }

    private int solve(int n) {
        if (n == 2) return 1;
        if (memo[n] != null) return memo[n];
        int best = 0;
        for (int i = 1; i < n; i++) {
            best = Math.max(best, Math.max(i * (n - i), i * solve(n - i)));
        }
        memo[n] = best;
        return best;
    }
}
