/**
 * LeetCode 416. Partition Equal Subset Sum
 * Approach: Top-down memoized recursion -- canReach(i, remaining) tries
 * skipping or taking nums[i] to hit remaining == 0 exactly, using half the
 * array's total sum as the target.
 * Time: O(n * sum) | Space: O(n * sum)
 */
class Solution {
    private int[] nums;
    private Boolean[][] dp;

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 != 0) return false;
        this.nums = nums;
        this.dp = new Boolean[nums.length][sum / 2 + 1];
        return canReach(0, sum / 2);
    }

    private boolean canReach(int i, int remaining) {
        if (remaining == 0) return true;
        if (i == nums.length || remaining < 0) return false;
        if (dp[i][remaining] != null) return dp[i][remaining];
        boolean result = canReach(i + 1, remaining) || canReach(i + 1, remaining - nums[i]);
        dp[i][remaining] = result;
        return result;
    }
}
