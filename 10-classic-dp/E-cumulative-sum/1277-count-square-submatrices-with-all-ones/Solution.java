/**
 * LeetCode 1277. Count Square Submatrices with All Ones
 * Approach: Top-down memoized recursion -- sideAt(r, c), the side length
 * of the largest all-1's square bottom-right-anchored at (r,c), also
 * equals the NUMBER of all-1's squares anchored there (a side-k square
 * anchored here implies valid squares of every side 1..k anchored here
 * too), so summing sideAt over every cell gives the total count.
 * Time: O(rows * cols) | Space: O(rows * cols)
 */
class Solution {
    private int[][] grid;
    private Integer[][] dp;

    public int countSquares(int[][] matrix) {
        this.grid = matrix;
        int rows = matrix.length, cols = matrix[0].length;
        this.dp = new Integer[rows][cols];
        int total = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                total += sideAt(r, c);
            }
        }
        return total;
    }

    private int sideAt(int r, int c) {
        if (dp[r][c] != null) return dp[r][c];
        int result;
        if (grid[r][c] == 0) {
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
