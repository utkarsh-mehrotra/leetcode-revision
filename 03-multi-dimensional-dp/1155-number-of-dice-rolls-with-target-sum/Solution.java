/**
 * LeetCode 1155. Number of Dice Rolls With Target Sum
 * Approach: Top-down memoized recursion over (diceLeft, remainingTarget) --
 * sum, over every face value, the ways to hit the remaining target with
 * one fewer die.
 * Time: O(n * target * k) | Space: O(n * target)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private int k;
    private Integer[][] dp;

    public int numRollsToTarget(int n, int k, int target) {
        this.k = k;
        this.dp = new Integer[n + 1][target + 1];
        return ways(n, target);
    }

    private int ways(int diceLeft, int remaining) {
        if (diceLeft == 0) return remaining == 0 ? 1 : 0;
        if (remaining < 0) return 0;
        if (dp[diceLeft][remaining] != null) return dp[diceLeft][remaining];
        long total = 0;
        for (int face = 1; face <= k && face <= remaining; face++) {
            total = (total + ways(diceLeft - 1, remaining - face)) % MOD;
        }
        dp[diceLeft][remaining] = (int) total;
        return (int) total;
    }
}
