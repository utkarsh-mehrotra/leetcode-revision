/**
 * LeetCode 576. Out of Boundary Paths
 * Approach: Top-down memoized recursion over (row, col, movesLeft) --
 * a move that lands outside the grid contributes one path; otherwise sum
 * the paths from all 4 neighbors with one fewer move.
 * Time: O(m * n * maxMove) | Space: O(m * n * maxMove)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int m, n;
    private Integer[][][] dp;

    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        this.m = m;
        this.n = n;
        this.dp = new Integer[m][n][maxMove + 1];
        return solve(startRow, startColumn, maxMove);
    }

    private int solve(int row, int col, int movesLeft) {
        if (row < 0 || row >= m || col < 0 || col >= n) return 1; // fell off the boundary
        if (movesLeft == 0) return 0;
        if (dp[row][col][movesLeft] != null) return dp[row][col][movesLeft];
        long total = 0;
        for (int[] dir : DIRS) {
            total = (total + solve(row + dir[0], col + dir[1], movesLeft - 1)) % MOD;
        }
        dp[row][col][movesLeft] = (int) total;
        return (int) total;
    }
}
