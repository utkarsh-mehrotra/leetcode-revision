/**
 * LeetCode 338. Counting Bits
 * Approach: Top-down memoized recursion -- popcount(i) = popcount(i & (i-1)) + 1,
 * since clearing the lowest set bit reduces to an already-solved subproblem.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private Integer[] dp;

    public int[] countBits(int n) {
        dp = new Integer[n + 1];
        int[] result = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            result[i] = popcount(i);
        }
        return result;
    }

    private int popcount(int i) {
        if (i == 0) return 0;
        if (dp[i] != null) return dp[i];
        dp[i] = popcount(i & (i - 1)) + 1;
        return dp[i];
    }
}
