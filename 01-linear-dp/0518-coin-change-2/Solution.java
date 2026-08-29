/**
 * LeetCode 518. Coin Change 2
 * Approach: Unbounded knapsack counting DP. Iterating coins in the outer
 * loop and amounts in the inner loop (ascending) ensures each combination
 * is counted once regardless of coin order, avoiding permutation
 * over-counting.
 * Time: O(amount * coins.length) | Space: O(amount)
 */
class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int a = coin; a <= amount; a++) {
                dp[a] += dp[a - coin];
            }
        }
        return dp[amount];
    }
}
