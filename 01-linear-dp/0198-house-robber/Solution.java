/**
 * LeetCode 198. House Robber
 * Approach: Top-down memoized recursion -- best(i) is the max loot from
 * houses i..end, either skipping house i or robbing it and jumping to i+2.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private int[] nums;
    private Integer[] memo;

    public int rob(int[] nums) {
        this.nums = nums;
        this.memo = new Integer[nums.length];
        return best(0);
    }

    private int best(int i) {
        if (i >= nums.length) return 0;
        if (memo[i] != null) return memo[i];
        int result = Math.max(best(i + 1), nums[i] + best(i + 2));
        memo[i] = result;
        return result;
    }
}
