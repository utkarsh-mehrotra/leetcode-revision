/**
 * LeetCode 152. Maximum Product Subarray
 * Approach: Top-down memoized recursion -- because a negative number can
 * flip the sign, track both the max and min product ending at i (a min
 * can become the max if multiplied by another negative), each built from
 * i-1's max/min.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private int[] nums;
    private Long[][] dp; // dp[i] = {maxEndingAt, minEndingAt}

    public int maxProduct(int[] nums) {
        this.nums = nums;
        this.dp = new Long[nums.length][2];
        long best = Long.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            best = Math.max(best, endingAt(i)[0]);
        }
        return (int) best;
    }

    private Long[] endingAt(int i) {
        if (dp[i][0] != null) return new Long[]{dp[i][0], dp[i][1]};
        long num = nums[i];
        long max, min;
        if (i == 0) {
            max = num;
            min = num;
        } else {
            Long[] prev = endingAt(i - 1);
            long candidate1 = num * prev[0];
            long candidate2 = num * prev[1];
            max = Math.max(num, Math.max(candidate1, candidate2));
            min = Math.min(num, Math.min(candidate1, candidate2));
        }
        dp[i][0] = max;
        dp[i][1] = min;
        return new Long[]{max, min};
    }
}
