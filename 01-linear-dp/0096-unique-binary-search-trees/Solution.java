/**
 * LeetCode 96. Unique Binary Search Trees
 * Approach: Catalan number recurrence -- dp[i] = sum over root choices j of
 * dp[j-1] * dp[i-j] (left subtree size j-1, right subtree size i-j).
 * Time: O(n^2) | Space: O(n)
 */
class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int nodes = 1; nodes <= n; nodes++) {
            for (int root = 1; root <= nodes; root++) {
                dp[nodes] += dp[root - 1] * dp[nodes - root];
            }
        }
        return dp[n];
    }
}
