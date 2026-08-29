/**
 * LeetCode 303. Range Sum Query - Immutable
 * Approach: Top-down memoized recursion -- prefixSum(i) is the sum of the
 * first i elements, built from prefixSum(i-1) so each is computed once.
 * Time: O(n) preprocessing, O(1) per query | Space: O(n)
 */
class NumArray {
    private int[] nums;
    private Integer[] dp;

    public NumArray(int[] nums) {
        this.nums = nums;
        this.dp = new Integer[nums.length + 1];
    }

    public int sumRange(int left, int right) {
        return prefixSum(right + 1) - prefixSum(left);
    }

    private int prefixSum(int i) {
        if (i == 0) return 0;
        if (dp[i] != null) return dp[i];
        int result = prefixSum(i - 1) + nums[i - 1];
        dp[i] = result;
        return result;
    }
}
