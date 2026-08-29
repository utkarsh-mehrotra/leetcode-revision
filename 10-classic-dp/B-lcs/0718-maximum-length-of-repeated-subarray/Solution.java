/**
 * LeetCode 718. Maximum Length of Repeated Subarray
 * Approach: Top-down memoized recursion -- commonSuffixLen(i, j) is the
 * length of the longest common suffix ending exactly at nums1[i-1] and
 * nums2[j-1]; it only extends when those elements match, so the best
 * repeated subarray is the max of this value over every (i, j).
 * Time: O(m * n) | Space: O(m * n)
 */
class Solution {
    private int[] nums1, nums2;
    private Integer[][] dp;

    public int findLength(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        this.dp = new Integer[nums1.length + 1][nums2.length + 1];
        int best = 0;
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                best = Math.max(best, commonSuffixLen(i, j));
            }
        }
        return best;
    }

    private int commonSuffixLen(int i, int j) {
        if (i == 0 || j == 0) return 0;
        if (dp[i][j] != null) return dp[i][j];
        int result = (nums1[i - 1] == nums2[j - 1]) ? 1 + commonSuffixLen(i - 1, j - 1) : 0;
        dp[i][j] = result;
        return result;
    }
}
