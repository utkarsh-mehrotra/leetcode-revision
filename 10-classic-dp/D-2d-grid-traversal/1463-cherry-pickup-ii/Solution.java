/**
 * LeetCode 1463. Cherry Pickup II
 * Approach: Top-down memoized recursion over (row, col1, col2) -- both
 * robots descend one row at a time, each independently moving to
 * col-1/col/col+1. best(row, col1, col2) collects this row's cherries
 * (once, if both robots share a cell) plus the best over all 9
 * combinations of next-row moves.
 * Time: O(rows * cols^2 * 9) | Space: O(rows * cols^2)
 */
class Solution {
    private int[][] grid;
    private int rows, cols;
    private Integer[][][] dp;

    public int cherryPickup(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.dp = new Integer[rows][cols][cols];
        return best(0, 0, cols - 1);
    }

    private int best(int row, int col1, int col2) {
        if (row == rows) return 0;
        if (dp[row][col1][col2] != null) return dp[row][col1][col2];
        int cherries = grid[row][col1] + (col1 == col2 ? 0 : grid[row][col2]);
        int bestNext = 0;
        for (int d1 = -1; d1 <= 1; d1++) {
            int nc1 = col1 + d1;
            if (nc1 < 0 || nc1 >= cols) continue;
            for (int d2 = -1; d2 <= 1; d2++) {
                int nc2 = col2 + d2;
                if (nc2 < 0 || nc2 >= cols) continue;
                bestNext = Math.max(bestNext, best(row + 1, nc1, nc2));
            }
        }
        int result = cherries + bestNext;
        dp[row][col1][col2] = result;
        return result;
    }
}
