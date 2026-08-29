/**
 * LeetCode 1563. Stone Game V
 * Approach: Interval DP -- best(lo, hi) is the max score Alice can force
 * from stoneValue[lo..hi], trying every split point k: whichever side has
 * the smaller sum is kept (its sum is scored and play continues there);
 * on a tie either side may be kept, so both are tried.
 * Time: O(n^3) | Space: O(n^2)
 */
class Solution {
    private int[] prefixSum;
    private Integer[][] dp;

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) prefixSum[i + 1] = prefixSum[i] + stoneValue[i];
        dp = new Integer[n][n];
        return best(0, n - 1);
    }

    private int rangeSum(int lo, int hi) {
        return prefixSum[hi + 1] - prefixSum[lo];
    }

    private int best(int lo, int hi) {
        if (lo == hi) return 0;
        if (dp[lo][hi] != null) return dp[lo][hi];
        int result = 0;
        for (int k = lo; k < hi; k++) {
            int leftSum = rangeSum(lo, k);
            int rightSum = rangeSum(k + 1, hi);
            if (leftSum <= rightSum) {
                result = Math.max(result, leftSum + best(lo, k));
            }
            if (rightSum <= leftSum) {
                result = Math.max(result, rightSum + best(k + 1, hi));
            }
        }
        dp[lo][hi] = result;
        return result;
    }
}
