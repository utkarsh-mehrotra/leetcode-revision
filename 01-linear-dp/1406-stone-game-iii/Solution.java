/**
 * LeetCode 1406. Stone Game III
 * Approach: Suffix DP -- dp[i] is the best score differential (current
 * player minus opponent) achievable from stoneValue[i..end]. The mover at
 * i chooses to take 1, 2, or 3 stones, and the resulting differential is
 * that take's sum minus the best the opponent can then achieve.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int[n + 1]; // dp[n] = 0, empty pile
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Integer.MIN_VALUE;
            int take = 0;
            for (int k = 0; k < 3 && i + k < n; k++) {
                take += stoneValue[i + k];
                dp[i] = Math.max(dp[i], take - dp[i + k + 1]);
            }
        }
        if (dp[0] > 0) return "Alice";
        if (dp[0] < 0) return "Bob";
        return "Tie";
    }
}
