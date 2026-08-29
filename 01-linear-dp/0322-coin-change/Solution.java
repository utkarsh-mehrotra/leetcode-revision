import java.util.Arrays;

/**
 * LeetCode 322. Coin Change
 * Approach: Unbounded knapsack DP -- dp[a] = fewest coins to make amount a,
 * built forward from dp[a - coin] for every coin <= a.
 * Time: O(amount * coins.length) | Space: O(amount)
 */
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int a = 1; a <= amount; a++) {
            for (int coin : coins) {
                if (coin <= a) {
                    dp[a] = Math.min(dp[a], dp[a - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
