/**
 * LeetCode 64. Minimum Path Sum
 * Approach: Top-down memoized recursion -- best(r, c) is the min path sum
 * from (0,0) to (r,c) moving only right or down.
 * Time: O(m * n) | Space: O(m * n)
 */
class Solution {
    private int[][] grid;
    private Integer[][] dp;

    public int minPathSum(int[][] grid) {
        this.grid = grid;
        int m = grid.length, n = grid[0].length;
        this.dp = new Integer[m][n];
        return best(m - 1, n - 1);
    }

    private int best(int r, int c) {
        if (r == 0 && c == 0) return grid[0][0];
        if (dp[r][c] != null) return dp[r][c];
        int fromAbove = (r > 0) ? best(r - 1, c) : Integer.MAX_VALUE;
        int fromLeft = (c > 0) ? best(r, c - 1) : Integer.MAX_VALUE;
        int result = grid[r][c] + Math.min(fromAbove, fromLeft);
        dp[r][c] = result;
        return result;
    }
}
