/**
 * LeetCode 494. Target Sum
 * Approach: Top-down memoized recursion over (index, runningSum) -- at each
 * number, branch into adding it or subtracting it, counting the ways that
 * land on `target` after processing every number. The sum is offset by
 * totalSum so it can index a dense array instead of a hash map.
 * Time: O(n * totalSum) | Space: O(n * totalSum)
 */
class Solution {
    private int[] nums;
    private int target;
    private int offset;
    private Integer[][] dp;

    public int findTargetSumWays(int[] nums, int target) {
        this.nums = nums;
        this.target = target;
        int totalSum = 0;
        for (int num : nums) totalSum += num;
        if (target > totalSum || target < -totalSum) return 0;
        this.offset = totalSum;
        this.dp = new Integer[nums.length][2 * totalSum + 1];
        return solve(0, 0);
    }

    private int solve(int i, int sum) {
        if (i == nums.length) return sum == target ? 1 : 0;
        if (dp[i][sum + offset] != null) return dp[i][sum + offset];
        int result = solve(i + 1, sum + nums[i]) + solve(i + 1, sum - nums[i]);
        dp[i][sum + offset] = result;
        return result;
    }
}
