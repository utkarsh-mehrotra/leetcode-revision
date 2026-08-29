/**
 * LeetCode 96. Unique Binary Search Trees
 * Approach: Top-down memoized recursion -- numTrees(n) sums, over every
 * root choice, the product of the ways to build the left and right subtrees.
 * Time: O(n^2) | Space: O(n)
 */
class Solution {
    private Integer[] dp;

    public int numTrees(int n) {
        dp = new Integer[n + 1];
        return solve(n);
    }

    private int solve(int nodes) {
        if (nodes <= 1) return 1;
        if (dp[nodes] != null) return dp[nodes];
        int total = 0;
        for (int root = 1; root <= nodes; root++) {
            // root splits [1..nodes] into a left subtree of size root-1 and a right of size nodes-root.
            total += solve(root - 1) * solve(nodes - root);
        }
        dp[nodes] = total;
        return total;
    }
}
