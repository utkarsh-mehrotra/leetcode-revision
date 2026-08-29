/**
 * LeetCode 309. Best Time to Buy and Sell Stock with Cooldown
 * Approach: Top-down memoized recursion over (day, state) where state is
 * 0 = free to buy, 1 = holding, 2 = cooldown (must skip one day after a sale).
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private int[] prices;
    private Integer[][] memo;

    public int maxProfit(int[] prices) {
        this.prices = prices;
        this.memo = new Integer[prices.length][3];
        return solve(0, 0);
    }

    private int solve(int day, int state) {
        if (day >= prices.length) return 0;
        if (memo[day][state] != null) return memo[day][state];
        int result;
        if (state == 0) {
            result = Math.max(solve(day + 1, 0), -prices[day] + solve(day + 1, 1));
        } else if (state == 1) {
            result = Math.max(solve(day + 1, 1), prices[day] + solve(day + 1, 2));
        } else {
            result = solve(day + 1, 0);
        }
        memo[day][state] = result;
        return result;
    }
}
