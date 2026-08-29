/**
 * LeetCode 53. Maximum Subarray
 * Approach: Top-down memoized recursion (Kadane's algorithm) -- best(i) is
 * the max subarray sum ending exactly at i, either starting fresh at
 * nums[i] or extending the best sum ending at i-1.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private int[] nums;
    private Integer[] dp;

    public int maxSubArray(int[] nums) {
        this.nums = nums;
        this.dp = new Integer[nums.length];
        int best = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            best = Math.max(best, endingAt(i));
        }
        return best;
    }

    private int endingAt(int i) {
        if (i == 0) return nums[0];
        if (dp[i] != null) return dp[i];
        int result = nums[i] + Math.max(0, endingAt(i - 1));
        dp[i] = result;
        return result;
    }
}
