/**
 * LeetCode 1388. Pizza With 3n Slices
 * Approach: Choosing n = slices.length/3 non-adjacent slices from a circle
 * reduces to two linear passes (exclude the first slice, or exclude the
 * last), each solved by top-down memoized recursion over (index,
 * slicesLeft) -- skip slice i or take it and jump to i+2.
 * Time: O(n^2) | Space: O(n^2)
 */
class Solution {
    private int[] slices;
    private Integer[][] dp;

    public int maxSizeSlices(int[] slices) {
        this.slices = slices;
        int n = slices.length / 3;
        int excludeLast = bestPick(0, slices.length - 2, n);
        int excludeFirst = bestPick(1, slices.length - 1, n);
        return Math.max(excludeLast, excludeFirst);
    }

    private int bestPick(int lo, int hi, int k) {
        dp = new Integer[hi - lo + 2][k + 1];
        return pick(lo, hi, k, lo);
    }

    private int pick(int i, int hi, int k, int lo) {
        if (k == 0) return 0;
        // Fewer than 2k-1 slices remain -- not enough room for k non-adjacent picks.
        if (hi - i + 1 < 2 * k - 1) return Integer.MIN_VALUE / 2;
        int idx = i - lo;
        if (dp[idx][k] != null) return dp[idx][k];
        int skip = pick(i + 1, hi, k, lo);
        int take = slices[i] + pick(i + 2, hi, k - 1, lo);
        int result = Math.max(skip, take);
        dp[idx][k] = result;
        return result;
    }
}
