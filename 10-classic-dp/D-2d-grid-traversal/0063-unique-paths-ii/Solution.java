/**
 * LeetCode 63. Unique Paths II
 * Approach: Top-down memoized recursion -- ways(r, c) is the number of
 * obstacle-free paths from (0,0) to (r,c), built from the cell above plus
 * the cell to the left; any obstacle cell contributes 0 paths.
 * Time: O(m * n) | Space: O(m * n)
 */
class Solution {
    private int[][] grid;
    private Integer[][] dp;

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        this.grid = obstacleGrid;
        int m = grid.length, n = grid[0].length;
        this.dp = new Integer[m][n];
        return ways(m - 1, n - 1);
    }

    private int ways(int r, int c) {
        if (r < 0 || c < 0 || grid[r][c] == 1) return 0;
        if (r == 0 && c == 0) return 1;
        if (dp[r][c] != null) return dp[r][c];
        int result = ways(r - 1, c) + ways(r, c - 1);
        dp[r][c] = result;
        return result;
    }
}
