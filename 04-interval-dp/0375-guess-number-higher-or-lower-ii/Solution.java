/**
 * LeetCode 375. Guess Number Higher or Lower II
 * Approach: Minimax interval DP -- cost(lo, hi) is the guaranteed amount
 * needed to win against a worst-case answer in [lo, hi]. Guessing g costs
 * g plus whichever remaining half (below or above g) is worse for us; we
 * pick the guess that minimizes that worst case.
 * Time: O(n^3) | Space: O(n^2)
 */
class Solution {
    private Integer[][] dp;

    public int getMoneyAmount(int n) {
        dp = new Integer[n + 2][n + 2];
        return cost(1, n);
    }

    private int cost(int lo, int hi) {
        if (lo >= hi) return 0;
        if (dp[lo][hi] != null) return dp[lo][hi];
        int best = Integer.MAX_VALUE;
        for (int guess = lo; guess <= hi; guess++) {
            int worstCase = guess + Math.max(cost(lo, guess - 1), cost(guess + 1, hi));
            best = Math.min(best, worstCase);
        }
        dp[lo][hi] = best;
        return best;
    }
}
