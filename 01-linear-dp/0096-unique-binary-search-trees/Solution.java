/**
 * LeetCode 96. Unique Binary Search Trees
 * Approach: Top-down memoized recursion -- numTrees(n) sums, over every
 * root choice, the product of the ways to build the left and right subtrees.
 * Time: O(n^2) | Space: O(n)
 */
class Solution {
    private Integer[] memo;

    public int numTrees(int n) {
        memo = new Integer[n + 1];
        return solve(n);
    }

    private int solve(int nodes) {
        if (nodes <= 1) return 1;
        if (memo[nodes] != null) return memo[nodes];
        int total = 0;
        for (int root = 1; root <= nodes; root++) {
            total += solve(root - 1) * solve(nodes - root);
        }
        memo[nodes] = total;
        return total;
    }
}
