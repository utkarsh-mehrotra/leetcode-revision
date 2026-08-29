/**
 * LeetCode 2328. Number of Increasing Paths in a Grid
 * Approach: Top-down memoized DFS, the counting twin of 329's Longest
 * Increasing Path. dp[r][c] = number of strictly-increasing paths
 * STARTING at (r,c) (a length-1 path by itself, plus one extension for
 * every greater neighbor's own path count). Since values strictly
 * increase along any path, the transition graph is acyclic, so each
 * cell's count is memoized and computed exactly once; the final answer
 * sums every cell's count as a distinct starting point, mod 1e9+7.
 * Time: O(rows*cols) | Space: O(rows*cols)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int[][] grid;
    private Long[][] dp;
    private int rows, cols;

    public int countPaths(int[][] grid) {
        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;
        dp = new Long[rows][cols];

        long total = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                total = (total + solve(r, c)) % MOD;
            }
        }
        return (int) total;
    }

    private long solve(int r, int c) {
        if (dp[r][c] != null) return dp[r][c];
        long count = 1;
        for (int[] d : DIRS) {
            int nr = r + d[0], nc = c + d[1];
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] > grid[r][c]) {
                count = (count + solve(nr, nc)) % MOD;
            }
        }
        dp[r][c] = count;
        return count;
    }
}
