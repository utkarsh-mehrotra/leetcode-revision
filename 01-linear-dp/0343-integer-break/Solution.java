/**
 * LeetCode 343. Integer Break
 * Approach: Top-down memoized recursion -- breakInt(n) tries every first
 * piece i and takes the best of leaving the rest whole (i*(n-i)) or
 * breaking it further (i*breakInt(n-i)).
 * Time: O(n^2) | Space: O(n)
 */
class Solution {
    private Integer[] dp;

    public int integerBreak(int n) {
        dp = new Integer[n + 1];
        return solve(n);
    }

    private int solve(int n) {
        if (n == 2) return 1;
        if (dp[n] != null) return dp[n];
        int best = 0;
        for (int i = 1; i < n; i++) {
            // Compare leaving the remainder (n - i) whole vs. breaking it further.
            best = Math.max(best, Math.max(i * (n - i), i * solve(n - i)));
        }
        dp[n] = best;
        return best;
    }
}
