/**
 * LeetCode 377. Combination Sum IV
 * Approach: Top-down memoized recursion -- ways(remaining) sums, over every
 * number, the ways to fill the rest after using that number first. Since
 * order matters (this counts permutations, not combinations), every number
 * is tried at every position rather than only from a fixed start index.
 * Time: O(target * nums.length) | Space: O(target)
 */
class Solution {
    private int[] nums;
    private Integer[] dp;

    public int combinationSum4(int[] nums, int target) {
        this.nums = nums;
        this.dp = new Integer[target + 1];
        return ways(target);
    }

    private int ways(int remaining) {
        if (remaining == 0) return 1;
        if (dp[remaining] != null) return dp[remaining];
        int result = 0;
        for (int num : nums) {
            if (num <= remaining) {
                result += ways(remaining - num);
            }
        }
        dp[remaining] = result;
        return result;
    }
}
