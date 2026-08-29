/**
 * LeetCode 931. Minimum Falling Path Sum
 * Approach: Top-down memoized recursion -- best(row, col) is the min sum
 * from this cell down to the last row, built from the cheapest of the (up
 * to) 3 reachable cells in the row below.
 * Time: O(n^2) | Space: O(n^2)
 */
class Solution {
    private int[][] matrix;
    private int n;
    private Integer[][] dp;

    public int minFallingPathSum(int[][] matrix) {
        this.matrix = matrix;
        this.n = matrix.length;
        this.dp = new Integer[n][n];
        int best = Integer.MAX_VALUE;
        for (int col = 0; col < n; col++) {
            best = Math.min(best, solve(0, col));
        }
        return best;
    }

    private int solve(int row, int col) {
        if (dp[row][col] != null) return dp[row][col];
        int below = (row == n - 1) ? 0 : Math.min(
            solve(row + 1, col),
            Math.min(col > 0 ? solve(row + 1, col - 1) : Integer.MAX_VALUE,
                     col < n - 1 ? solve(row + 1, col + 1) : Integer.MAX_VALUE));
        int result = matrix[row][col] + below;
        dp[row][col] = result;
        return result;
    }
}
