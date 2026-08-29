/**
 * LeetCode 1690. Stone Game VII
 * Approach: Interval DP -- diff(lo, hi) is the best score differential the
 * current mover can force from stones[lo..hi]. Removing an end pile scores
 * the sum of everything else remaining, so each choice's value is the
 * other side's suffix/prefix sum minus the opponent's best from what's left.
 * Time: O(n^2) | Space: O(n^2)
 */
class Solution {
    private int[] stones;
    private int[] prefixSum;
    private Integer[][] dp;

    public int stoneGameVII(int[] stones) {
        this.stones = stones;
        int n = stones.length;
        prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) prefixSum[i + 1] = prefixSum[i] + stones[i];
        dp = new Integer[n][n];
        return diff(0, n - 1);
    }

    private int rangeSum(int lo, int hi) { // sum of stones[lo..hi] inclusive
        return prefixSum[hi + 1] - prefixSum[lo];
    }

    private int diff(int lo, int hi) {
        if (lo == hi) return 0;
        if (dp[lo][hi] != null) return dp[lo][hi];
        int removeLeft = rangeSum(lo + 1, hi) - diff(lo + 1, hi);
        int removeRight = rangeSum(lo, hi - 1) - diff(lo, hi - 1);
        int result = Math.max(removeLeft, removeRight);
        dp[lo][hi] = result;
        return result;
    }
}
