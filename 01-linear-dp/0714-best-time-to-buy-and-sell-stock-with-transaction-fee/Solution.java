/**
 * LeetCode 714. Best Time to Buy and Sell Stock with Transaction Fee
 * Approach: Top-down memoized recursion over (day, holding); the fee is
 * charged once at sale time.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private int[] prices;
    private int fee;
    private Integer[][] dp;

    public int maxProfit(int[] prices, int fee) {
        this.prices = prices;
        this.fee = fee;
        this.dp = new Integer[prices.length][2];
        return solve(0, 0);
    }

    private int solve(int day, int holding) {
        if (day == prices.length) return 0;
        if (dp[day][holding] != null) return dp[day][holding];
        int skip = solve(day + 1, holding);
        int act = (holding == 0)
            ? -prices[day] + solve(day + 1, 1)
            : prices[day] - fee + solve(day + 1, 0);
        int result = Math.max(skip, act);
        dp[day][holding] = result;
        return result;
    }
}
