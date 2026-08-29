/**
 * LeetCode 877. Stone Game
 * Approach: Interval DP -- diff(lo, hi) is the best score differential the
 * current mover can force from piles[lo..hi], taking whichever end pile
 * leaves the opponent with the worse remaining interval.
 * Time: O(n^2) | Space: O(n^2)
 */
class Solution {
    private int[] piles;
    private Integer[][] dp;

    public boolean stoneGame(int[] piles) {
        this.piles = piles;
        this.dp = new Integer[piles.length][piles.length];
        return diff(0, piles.length - 1) > 0;
    }

    private int diff(int lo, int hi) {
        if (lo == hi) return piles[lo];
        if (dp[lo][hi] != null) return dp[lo][hi];
        int result = Math.max(piles[lo] - diff(lo + 1, hi), piles[hi] - diff(lo, hi - 1));
        dp[lo][hi] = result;
        return result;
    }
}
