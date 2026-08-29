/**
 * LeetCode 1359. Count All Valid Pickup and Delivery Options
 * Approach: Top-down memoized recursion -- ways(i) placing i pairs builds
 * on ways(i-1) by inserting the i-th pickup/delivery pair in i*(2i-1) ways.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private Long[] dp;

    public int countOrders(int n) {
        dp = new Long[n + 1];
        return (int) ways(n);
    }

    private long ways(int i) {
        if (i == 0) return 1;
        if (dp[i] != null) return dp[i];
        long result = (ways(i - 1) * i % MOD) * (2 * i - 1) % MOD;
        dp[i] = result;
        return result;
    }
}
