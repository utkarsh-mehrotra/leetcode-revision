/**
 * LeetCode 650. 2 Keys Keyboard
 * Approach: Top-down memoized recursion -- ops(n) = min over every proper
 * divisor d of n of ops(d) + (n / d), representing "grow a d-length buffer
 * to n via one copy-all followed by (n/d - 1) pastes."
 * Time: O(n^2) | Space: O(n)
 */
class Solution {
    private Integer[] dp;

    public int minSteps(int n) {
        dp = new Integer[n + 1];
        return ops(n);
    }

    private int ops(int n) {
        if (n == 1) return 0;
        if (dp[n] != null) return dp[n];
        int best = Integer.MAX_VALUE;
        for (int d = 1; d < n; d++) {
            if (n % d == 0) {
                best = Math.min(best, ops(d) + n / d);
            }
        }
        dp[n] = best;
        return best;
    }
}
