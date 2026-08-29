/**
 * LeetCode 1000. Minimum Cost to Merge Stones
 * Approach: Interval DP over (lo, hi, piles) -- best(lo, hi, piles) is the
 * min cost merging stones[lo..hi] down to exactly `piles` piles. Splitting
 * into more than 1 pile just partitions the range at some mid point (only
 * mid offsets that keep the left part reducible to exactly 1 pile are
 * legal, stepping by K-1); collapsing to exactly 1 pile from K piles costs
 * the range's total sum on top of first reaching K piles. Impossible
 * overall unless (n-1) is divisible by (K-1).
 * Time: O(n^3 / K) | Space: O(n^2 * K)
 */
class Solution {
    private int[] stones;
    private int[] prefixSum;
    private int k;
    private Integer[][][] dp;
    private static final int INF = Integer.MAX_VALUE / 2;

    public int mergeStones(int[] stones, int k) {
        int n = stones.length;
        if ((n - 1) % (k - 1) != 0) return -1;
        this.stones = stones;
        this.k = k;
        prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) prefixSum[i + 1] = prefixSum[i] + stones[i];
        dp = new Integer[n][n][k + 1];
        return best(0, n - 1, 1);
    }

    private int rangeSum(int lo, int hi) {
        return prefixSum[hi + 1] - prefixSum[lo];
    }

    private int best(int lo, int hi, int piles) {
        if (lo == hi) return piles == 1 ? 0 : INF;
        if (piles == 1) {
            int mergeToK = best(lo, hi, k);
            return mergeToK >= INF ? INF : mergeToK + rangeSum(lo, hi);
        }
        if (dp[lo][hi][piles] != null) return dp[lo][hi][piles];
        int result = INF;
        for (int mid = lo; mid < hi; mid += k - 1) {
            int left = best(lo, mid, 1);
            int right = best(mid + 1, hi, piles - 1);
            if (left < INF && right < INF) {
                result = Math.min(result, left + right);
            }
        }
        dp[lo][hi][piles] = result;
        return result;
    }
}
