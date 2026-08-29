/**
 * LeetCode 213. House Robber II
 * Approach: Houses form a circle, so house 0 and house n-1 can't both be
 * robbed. Top-down memoized recursion (identical in shape to House Robber)
 * is run twice -- once excluding the last house, once excluding the
 * first -- and we take the better of the two.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private int[] nums;
    private Integer[] dp;
    private int lo;

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        this.nums = nums;
        return Math.max(robRange(0, n - 2), robRange(1, n - 1));
    }

    // Linear House Robber over the closed range [lo, hi].
    private int robRange(int lo, int hi) {
        this.lo = lo;
        dp = new Integer[hi - lo + 1];
        return best(lo, hi);
    }

    private int best(int i, int hi) {
        if (i > hi) return 0;
        int idx = i - lo;
        if (dp[idx] != null) return dp[idx];
        int result = Math.max(best(i + 1, hi), nums[i] + best(i + 2, hi));
        dp[idx] = result;
        return result;
    }
}
