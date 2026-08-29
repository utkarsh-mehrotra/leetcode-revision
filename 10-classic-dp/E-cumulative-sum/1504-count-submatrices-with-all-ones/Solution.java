/**
 * LeetCode 1504. Count Submatrices With All Ones
 * Approach: Top-down memoized recursion -- rowRunEndingAt(r, c) is the
 * length of the run of consecutive 1's ending at (r,c) within its row.
 * For each cell as a rectangle's bottom-right corner, walk upward
 * tracking the narrowest row-run seen so far (the rectangle can only be
 * as wide as its narrowest row); each such width contributes one valid
 * all-ones rectangle.
 * Time: O(rows * cols^2) worst case, O(rows^2 * cols) via the row-scan below | Space: O(rows * cols)
 */
class Solution {
    private int[][] mat;
    private int rows, cols;
    private Integer[][] dp;

    public int numSubmat(int[][] mat) {
        this.mat = mat;
        rows = mat.length;
        cols = mat[0].length;
        dp = new Integer[rows][cols];

        int total = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int minWidth = Integer.MAX_VALUE;
                for (int up = r; up >= 0; up--) {
                    int run = rowRunEndingAt(up, c);
                    if (run == 0) break;
                    minWidth = Math.min(minWidth, run);
                    total += minWidth;
                }
            }
        }
        return total;
    }

    private int rowRunEndingAt(int r, int c) {
        if (mat[r][c] == 0) return 0;
        if (dp[r][c] != null) return dp[r][c];
        int result = 1 + (c > 0 ? rowRunEndingAt(r, c - 1) : 0);
        dp[r][c] = result;
        return result;
    }
}
