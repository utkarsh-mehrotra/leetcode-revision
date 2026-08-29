/**
 * LeetCode 329. Longest Increasing Path in a Matrix
 * Approach: Top-down memoized DFS. dp[r][c] = length of the longest
 * strictly-increasing path starting at (r,c), moving to any of the 4
 * neighbors with a strictly greater value. Since values only increase
 * along a path, the implicit graph is a DAG (no cycles are possible), so
 * memoizing each cell's answer is safe and every cell is solved exactly
 * once.
 * Time: O(rows*cols) | Space: O(rows*cols)
 */
class Solution {
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int[][] matrix;
    private int[][] dp;
    private int rows, cols;

    public int longestIncreasingPath(int[][] matrix) {
        this.matrix = matrix;
        rows = matrix.length;
        cols = matrix[0].length;
        dp = new int[rows][cols];

        int best = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                best = Math.max(best, solve(r, c));
            }
        }
        return best;
    }

    private int solve(int r, int c) {
        if (dp[r][c] != 0) return dp[r][c];
        int longest = 1;
        for (int[] d : DIRS) {
            int nr = r + d[0], nc = c + d[1];
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && matrix[nr][nc] > matrix[r][c]) {
                longest = Math.max(longest, 1 + solve(nr, nc));
            }
        }
        dp[r][c] = longest;
        return longest;
    }
}
