/**
 * LeetCode 221. Maximal Square
 * Approach: Top-down memoized recursion -- sideAt(r, c) is the side
 * length of the largest all-'1's square whose bottom-right corner is
 * (r,c), limited by the smallest of the square ending above, to the
 * left, and diagonally up-left (extending any further would need all
 * three to support it).
 * Time: O(rows * cols) | Space: O(rows * cols)
 */
class Solution {
    private char[][] grid;
    private Integer[][] dp;

    public int maximalSquare(char[][] matrix) {
        this.grid = matrix;
        int rows = matrix.length, cols = matrix[0].length;
        this.dp = new Integer[rows][cols];
        int best = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                best = Math.max(best, sideAt(r, c));
            }
        }
        return best * best;
    }

    private int sideAt(int r, int c) {
        if (dp[r][c] != null) return dp[r][c];
        int result;
        if (grid[r][c] == '0') {
            result = 0;
        } else {
            int up = (r > 0) ? sideAt(r - 1, c) : 0;
            int left = (c > 0) ? sideAt(r, c - 1) : 0;
            int diag = (r > 0 && c > 0) ? sideAt(r - 1, c - 1) : 0;
            result = 1 + Math.min(up, Math.min(left, diag));
        }
        dp[r][c] = result;
        return result;
    }
}
