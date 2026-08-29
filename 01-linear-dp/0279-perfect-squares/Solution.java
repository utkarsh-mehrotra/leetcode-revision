import java.util.Arrays;

/**
 * LeetCode 279. Perfect Squares
 * Approach: Bottom-up DP -- dp[i] is the fewest perfect-square numbers
 * summing to i, built from dp[i - j*j] for every square j*j <= i.
 * Time: O(n * sqrt(n)) | Space: O(n)
 */
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}
