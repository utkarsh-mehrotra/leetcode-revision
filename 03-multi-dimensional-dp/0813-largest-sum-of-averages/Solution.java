/**
 * LeetCode 813. Largest Sum of Averages
 * Approach: Top-down memoized recursion -- best(i, groupsLeft) is the max
 * sum of group averages for nums[i:] split into at most groupsLeft groups;
 * with 1 group left the answer is just the average of the whole suffix,
 * otherwise try every first-group end point j and recurse on the rest.
 * Prefix sums give each candidate group's average in O(1).
 * Time: O(n^2 * k) | Space: O(n * k)
 */
class Solution {
    private double[] prefixSum;
    private int n;
    private Double[][] dp;

    public double largestSumOfAverages(int[] nums, int k) {
        n = nums.length;
        prefixSum = new double[n + 1];
        for (int i = 0; i < n; i++) prefixSum[i + 1] = prefixSum[i] + nums[i];
        dp = new Double[n][k + 1];
        return best(0, k);
    }

    private double average(int i, int j) { // average of nums[i..j)
        return (prefixSum[j] - prefixSum[i]) / (j - i);
    }

    private double best(int i, int groupsLeft) {
        if (groupsLeft == 1) return average(i, n);
        if (dp[i][groupsLeft] != null) return dp[i][groupsLeft];
        double result = average(i, n); // fall back to one big final group
        for (int j = i + 1; j < n; j++) {
            result = Math.max(result, average(i, j) + best(j, groupsLeft - 1));
        }
        dp[i][groupsLeft] = result;
        return result;
    }
}
