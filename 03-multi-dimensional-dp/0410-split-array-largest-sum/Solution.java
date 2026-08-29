/**
 * LeetCode 410. Split Array Largest Sum
 * Approach: Top-down memoized recursion -- best(i, groupsLeft) is the
 * smallest possible "largest subarray sum" splitting nums[i:] into
 * groupsLeft parts; with 1 part left it's just the sum of the suffix,
 * otherwise try every split point and minimize the worse of the two sides.
 * Time: O(n^2 * m) | Space: O(n * m)
 */
class Solution {
    private int[] prefixSum;
    private int n;
    private Integer[][] dp;

    public int splitArray(int[] nums, int m) {
        n = nums.length;
        prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) prefixSum[i + 1] = prefixSum[i] + nums[i];
        dp = new Integer[n][m + 1];
        return best(0, m);
    }

    private int rangeSum(int i, int j) { // sum of nums[i..j)
        return prefixSum[j] - prefixSum[i];
    }

    private int best(int i, int groupsLeft) {
        if (groupsLeft == 1) return rangeSum(i, n);
        if (dp[i][groupsLeft] != null) return dp[i][groupsLeft];
        int result = Integer.MAX_VALUE;
        for (int j = i + 1; j <= n - (groupsLeft - 1); j++) {
            int candidate = Math.max(rangeSum(i, j), best(j, groupsLeft - 1));
            result = Math.min(result, candidate);
        }
        dp[i][groupsLeft] = result;
        return result;
    }
}
