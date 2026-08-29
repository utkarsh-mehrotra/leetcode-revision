/**
 * LeetCode 338. Counting Bits
 * Approach: dp[i] = dp[i & (i - 1)] + 1, since i & (i - 1) clears the
 * lowest set bit of i, reusing the previously computed popcount.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public int[] countBits(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i & (i - 1)] + 1;
        }
        return dp;
    }
}
