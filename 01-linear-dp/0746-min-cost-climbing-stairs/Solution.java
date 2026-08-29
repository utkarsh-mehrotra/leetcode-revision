/**
 * LeetCode 746. Min Cost Climbing Stairs
 * Approach: Top-down memoized recursion -- minCost(i) is the cheapest way
 * to reach step i, built from minCost(i-1) and minCost(i-2).
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private int[] cost;
    private Integer[] dp;

    public int minCostClimbingStairs(int[] cost) {
        this.cost = cost;
        this.dp = new Integer[cost.length + 1];
        return solve(cost.length);
    }

    private int solve(int i) {
        if (i <= 1) return 0;
        if (dp[i] != null) return dp[i];
        int result = Math.min(solve(i - 1) + cost[i - 1], solve(i - 2) + cost[i - 2]);
        dp[i] = result;
        return result;
    }
}
