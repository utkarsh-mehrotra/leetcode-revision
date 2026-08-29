/**
 * LeetCode 70. Climbing Stairs
 * Approach: Top-down memoized recursion -- ways(n) = ways(n-1) + ways(n-2),
 * each subproblem computed once and cached.
 * Time: O(n) | Space: O(n) (dp + recursion stack)
 */
class Solution {
    public int climbStairs(int n) {
        Integer[] dp = new Integer[n + 1];
        return climb(n, dp);
    }

    private int climb(int n, Integer[] dp) {
        if (n <= 2) return n;
        if (dp[n] != null) return dp[n];
        dp[n] = climb(n - 1, dp) + climb(n - 2, dp);
        return dp[n];
    }
}
