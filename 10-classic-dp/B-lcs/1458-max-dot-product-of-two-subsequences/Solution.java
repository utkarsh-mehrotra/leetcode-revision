/**
 * LeetCode 1458. Max Dot Product of Two Subsequences
 * Approach: Top-down memoized recursion over (i, j) -- best(i, j) is the
 * max dot product of two NON-EMPTY subsequences drawn from nums1[i:] and
 * nums2[j:]. Either pair nums1[i] with nums2[j] now (optionally extending
 * a positive-contributing continuation), or skip an element from either side.
 * Time: O(m * n) | Space: O(m * n)
 */
class Solution {
    private int[] nums1, nums2;
    private Integer[][] dp;
    private static final int NEG_INF = Integer.MIN_VALUE / 2;

    public int maxDotProduct(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        this.dp = new Integer[nums1.length][nums2.length];
        return best(0, 0);
    }

    private int best(int i, int j) {
        if (i == nums1.length || j == nums2.length) return NEG_INF;
        if (dp[i][j] != null) return dp[i][j];
        int pairHere = nums1[i] * nums2[j];
        int extended = best(i + 1, j + 1);
        int takeBoth = pairHere + Math.max(0, extended);
        int result = Math.max(takeBoth, Math.max(best(i + 1, j), best(i, j + 1)));
        dp[i][j] = result;
        return result;
    }
}
