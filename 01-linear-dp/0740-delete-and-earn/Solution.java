/**
 * LeetCode 740. Delete and Earn
 * Approach: Bucket the total value earnable per number, reducing the
 * problem to House Robber over consecutive values via top-down memoized
 * recursion (taking value v forbids v-1 and v+1).
 * Time: O(n + maxVal) | Space: O(maxVal)
 */
class Solution {
    private long[] earnings;
    private Long[] memo;

    public int deleteAndEarn(int[] nums) {
        int maxVal = 0;
        for (int num : nums) maxVal = Math.max(maxVal, num);
        earnings = new long[maxVal + 1];
        for (int num : nums) earnings[num] += num;
        memo = new Long[maxVal + 1];
        return (int) best(maxVal);
    }

    private long best(int v) {
        if (v < 0) return 0;
        if (v == 0) return earnings[0];
        if (memo[v] != null) return memo[v];
        long result = Math.max(best(v - 1), best(v - 2) + earnings[v]);
        memo[v] = result;
        return result;
    }
}
