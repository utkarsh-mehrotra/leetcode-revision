/**
 * LeetCode 123. Best Time to Buy and Sell Stock III
 * Approach: Top-down memoized recursion over (day, transactionsLeft, holding),
 * allowing at most 2 completed transactions.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private int[] prices;
    private Integer[][][] dp;

    public int maxProfit(int[] prices) {
        this.prices = prices;
        this.dp = new Integer[prices.length][3][2];
        return solve(0, 2, 0);
    }

    private int solve(int day, int txnLeft, int holding) {
        if (day == prices.length || txnLeft == 0) return 0;
        if (dp[day][txnLeft][holding] != null) return dp[day][txnLeft][holding];
        int skip = solve(day + 1, txnLeft, holding);
        int act = (holding == 1)
            ? prices[day] + solve(day + 1, txnLeft - 1, 0)
            : -prices[day] + solve(day + 1, txnLeft, 1);
        int result = Math.max(skip, act);
        dp[day][txnLeft][holding] = result;
        return result;
    }
}
