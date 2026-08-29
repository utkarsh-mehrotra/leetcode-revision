/**
 * LeetCode 121. Best Time to Buy and Sell Stock
 * Approach: Top-down memoized recursion over (day, state), where state is
 * 0 = not yet bought, 1 = currently holding. At most one buy and one sell
 * is ever performed, so selling collapses the remaining days to 0.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private int[] prices;
    private Integer[][] dp;

    public int maxProfit(int[] prices) {
        this.prices = prices;
        this.dp = new Integer[prices.length][2];
        return solve(0, 0);
    }

    private int solve(int day, int state) {
        if (day == prices.length) return 0;
        if (dp[day][state] != null) return dp[day][state];
        int skip = solve(day + 1, state);
        int act = (state == 0)
            ? -prices[day] + solve(day + 1, 1)
            : prices[day]; // sell now; nothing more can happen after
        int result = Math.max(skip, act);
        dp[day][state] = result;
        return result;
    }
}
