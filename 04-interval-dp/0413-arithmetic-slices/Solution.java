/**
 * LeetCode 413. Arithmetic Slices
 * Approach: Top-down memoized recursion -- endingAt(i) is the number of
 * arithmetic slices ending exactly at index i. If nums[i] extends the same
 * common difference as the slice ending at i-1, every one of those slices
 * (plus the new 3-element one) also ends at i.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private int[] nums;
    private Integer[] dp;

    public int numberOfArithmeticSlices(int[] nums) {
        this.nums = nums;
        this.dp = new Integer[nums.length];
        int total = 0;
        for (int i = 2; i < nums.length; i++) {
            total += endingAt(i);
        }
        return total;
    }

    private int endingAt(int i) {
        if (i < 2) return 0;
        if (dp[i] != null) return dp[i];
        int result = 0;
        if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
            result = endingAt(i - 1) + 1;
        }
        dp[i] = result;
        return result;
    }
}
