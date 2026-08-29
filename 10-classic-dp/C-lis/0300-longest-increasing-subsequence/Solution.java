/**
 * LeetCode 300. Longest Increasing Subsequence
 * Approach: Top-down memoized recursion -- lengthEndingAt(i) is the
 * longest increasing subsequence ending at i, built from the best
 * lengthEndingAt(j) over every earlier j with nums[j] < nums[i].
 * Time: O(n^2) | Space: O(n)
 */
class Solution {
    private int[] nums;
    private Integer[] dp;

    public int lengthOfLIS(int[] nums) {
        this.nums = nums;
        this.dp = new Integer[nums.length];
        int best = 1;
        for (int i = 0; i < nums.length; i++) {
            best = Math.max(best, lengthEndingAt(i));
        }
        return best;
    }

    private int lengthEndingAt(int i) {
        if (dp[i] != null) return dp[i];
        int best = 1;
        for (int j = 0; j < i; j++) {
            if (nums[j] < nums[i]) {
                best = Math.max(best, lengthEndingAt(j) + 1);
            }
        }
        dp[i] = best;
        return best;
    }
}
