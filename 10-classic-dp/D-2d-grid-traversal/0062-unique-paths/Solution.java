/**
 * LeetCode 62. Unique Paths
 * Approach: Top-down memoized recursion -- ways(r, c) is the number of
 * paths from (0,0) to (r,c) moving only right or down, built from the
 * cell above plus the cell to the left.
 * Time: O(m * n) | Space: O(m * n)
 */
class Solution {
    private Integer[][] dp;

    public int uniquePaths(int m, int n) {
        dp = new Integer[m][n];
        return ways(m - 1, n - 1);
    }

    private int ways(int r, int c) {
        if (r == 0 || c == 0) return 1;
        if (dp[r][c] != null) return dp[r][c];
        int result = ways(r - 1, c) + ways(r, c - 1);
        dp[r][c] = result;
        return result;
    }
}
