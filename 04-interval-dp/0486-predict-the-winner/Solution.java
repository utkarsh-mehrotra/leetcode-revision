/**
 * LeetCode 486. Predict the Winner
 * Approach: Interval DP -- diff(lo, hi) is the best score differential
 * (current mover minus opponent) achievable from nums[lo..hi], taking
 * whichever end leaves the opponent with the worse remaining interval.
 * Time: O(n^2) | Space: O(n^2)
 */
class Solution {
    private int[] nums;
    private Integer[][] dp;

    public boolean predictTheWinner(int[] nums) {
        this.nums = nums;
        this.dp = new Integer[nums.length][nums.length];
        return diff(0, nums.length - 1) >= 0;
    }

    private int diff(int lo, int hi) {
        if (lo == hi) return nums[lo];
        if (dp[lo][hi] != null) return dp[lo][hi];
        int result = Math.max(nums[lo] - diff(lo + 1, hi), nums[hi] - diff(lo, hi - 1));
        dp[lo][hi] = result;
        return result;
    }
}
