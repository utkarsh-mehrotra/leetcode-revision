/**
 * LeetCode 1130. Minimum Cost Tree From Leaf Values
 * Approach: Interval DP -- best(lo, hi) is the min non-leaf sum building a
 * tree whose in-order leaves are arr[lo..hi], trying every split point k
 * as the boundary between the left and right subtrees. maxInRange is
 * memoized separately so each range's max is computed once.
 * Time: O(n^3) | Space: O(n^2)
 */
class Solution {
    private int[] arr;
    private Integer[][] dp;
    private Integer[][] maxDp;

    public int mctFromLeafValues(int[] arr) {
        this.arr = arr;
        int n = arr.length;
        this.dp = new Integer[n][n];
        this.maxDp = new Integer[n][n];
        return best(0, n - 1);
    }

    private int maxInRange(int lo, int hi) {
        if (lo == hi) return arr[lo];
        if (maxDp[lo][hi] != null) return maxDp[lo][hi];
        int result = Math.max(arr[hi], maxInRange(lo, hi - 1));
        maxDp[lo][hi] = result;
        return result;
    }

    private int best(int lo, int hi) {
        if (lo == hi) return 0; // a single leaf needs no internal node
        if (dp[lo][hi] != null) return dp[lo][hi];
        int result = Integer.MAX_VALUE;
        for (int k = lo; k < hi; k++) {
            int cost = maxInRange(lo, k) * maxInRange(k + 1, hi);
            result = Math.min(result, best(lo, k) + best(k + 1, hi) + cost);
        }
        dp[lo][hi] = result;
        return result;
    }
}
