/**
 * LeetCode 801. Minimum Swaps to Make Sequences Increasing
 * Approach: Top-down memoized recursion over (index, prevSwapped) -- at
 * each position, try keeping and/or swapping nums1[i]/nums2[i], keeping
 * only the option(s) that stay strictly increasing relative to the
 * previous (possibly swapped) pair.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private int[] nums1, nums2;
    private int n;
    private Integer[][] dp;
    private static final int INF = Integer.MAX_VALUE / 2;

    public int minSwap(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        this.n = nums1.length;
        this.dp = new Integer[n][2];
        return Math.min(solve(1, false), 1 + solve(1, true));
    }

    // Min additional swaps for positions i..n-1, given whether position i-1 was swapped.
    private int solve(int i, boolean prevSwapped) {
        if (i == n) return 0;
        int key = prevSwapped ? 1 : 0;
        if (dp[i][key] != null) return dp[i][key];
        int prevVal1 = prevSwapped ? nums2[i - 1] : nums1[i - 1];
        int prevVal2 = prevSwapped ? nums1[i - 1] : nums2[i - 1];

        int result = INF;
        if (nums1[i] > prevVal1 && nums2[i] > prevVal2) {
            result = Math.min(result, solve(i + 1, false));
        }
        if (nums2[i] > prevVal1 && nums1[i] > prevVal2) {
            result = Math.min(result, 1 + solve(i + 1, true));
        }
        dp[i][key] = result;
        return result;
    }
}
