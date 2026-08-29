/**
 * LeetCode 1043. Partition Array for Maximum Sum
 * Approach: DP where dp[i] = best achievable sum for the prefix of length i.
 * For each i, try every partition-ending window of length 1..k ending at i,
 * replacing that window with its max value times its length.
 * Time: O(n * k) | Space: O(n)
 */
class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int windowMax = 0;
            for (int len = 1; len <= k && len <= i; len++) {
                windowMax = Math.max(windowMax, arr[i - len]);
                dp[i] = Math.max(dp[i], dp[i - len] + windowMax * len);
            }
        }
        return dp[n];
    }
}
