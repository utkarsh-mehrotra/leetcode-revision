/**
 * LeetCode 1140. Stone Game II
 * Approach: Top-down memoized recursion over (index, M) -- best(i, M) is
 * the max the current mover can score from piles[i:] given the current
 * "up to 2M piles" limit, trying every legal take count X and leaving the
 * opponent with the complementary best from piles[i+X:].
 * Time: O(n^2) | Space: O(n^2)
 */
class Solution {
    private int[] piles;
    private int n;
    private int[] suffixSum;
    private Integer[][] dp;

    public int stoneGameII(int[] piles) {
        this.piles = piles;
        this.n = piles.length;
        suffixSum = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) suffixSum[i] = suffixSum[i + 1] + piles[i];
        dp = new Integer[n][n + 1];
        return best(0, 1);
    }

    private int best(int i, int m) {
        if (i == n) return 0;
        int cappedM = Math.min(m, n); // M can't usefully exceed the number of piles left
        if (dp[i][cappedM] != null) return dp[i][cappedM];
        int result = 0;
        for (int x = 1; x <= 2 * cappedM && i + x <= n; x++) {
            int opponentBest = best(i + x, Math.max(cappedM, x));
            result = Math.max(result, suffixSum[i] - opponentBest);
        }
        dp[i][cappedM] = result;
        return result;
    }
}
