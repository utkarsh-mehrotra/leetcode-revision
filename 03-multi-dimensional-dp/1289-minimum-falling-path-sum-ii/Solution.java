/**
 * LeetCode 1289. Minimum Falling Path Sum II
 * Approach: Top-down memoized recursion -- best(row, col) is the min sum
 * from this cell down, using the cheapest reachable cell in the next row
 * that sits in a different column.
 * Time: O(n^3) | Space: O(n^2)
 */
class Solution {
    private int[][] grid;
    private int n;
    private Integer[][] dp;

    public int minFallingPathSum(int[][] grid) {
        this.grid = grid;
        this.n = grid.length;
        this.dp = new Integer[n][n];
        int best = Integer.MAX_VALUE;
        for (int col = 0; col < n; col++) {
            best = Math.min(best, solve(0, col));
        }
        return best;
    }

    private int solve(int row, int col) {
        if (dp[row][col] != null) return dp[row][col];
        int below = 0;
        if (row < n - 1) {
            below = Integer.MAX_VALUE;
            for (int nextCol = 0; nextCol < n; nextCol++) {
                if (nextCol != col) {
                    below = Math.min(below, solve(row + 1, nextCol));
                }
            }
        }
        int result = grid[row][col] + below;
        dp[row][col] = result;
        return result;
    }
}
