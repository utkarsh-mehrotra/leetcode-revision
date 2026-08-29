/**
 * LeetCode 322. Coin Change
 * Approach: Top-down memoized recursion -- coinChange(a) is 1 plus the
 * best of coinChange(a - coin) over every usable coin.
 * Time: O(amount * coins.length) | Space: O(amount)
 */
class Solution {
    private int[] coins;
    private Integer[] dp;
    private static final int INF = Integer.MAX_VALUE / 2;

    public int coinChange(int[] coins, int amount) {
        this.coins = coins;
        this.dp = new Integer[amount + 1];
        int result = solve(amount);
        return result >= INF ? -1 : result;
    }

    private int solve(int remaining) {
        if (remaining == 0) return 0;
        if (remaining < 0) return INF;
        if (dp[remaining] != null) return dp[remaining];
        int best = INF;
        for (int coin : coins) {
            best = Math.min(best, 1 + solve(remaining - coin));
        }
        dp[remaining] = best;
        return best;
    }
}
