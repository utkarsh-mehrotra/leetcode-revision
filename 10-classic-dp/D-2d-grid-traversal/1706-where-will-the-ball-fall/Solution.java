/**
 * LeetCode 1706. Where Will the Ball Fall
 * Approach: Top-down memoized recursion -- finalColumn(row, col) is which
 * column a ball dropped into (row, col) eventually exits from (or -1 if
 * it gets stuck), built from the outcome one row down after checking
 * whether the adjacent V-shaped walls block it. Balls dropped from
 * different starting columns can pass through the same (row, col) cell,
 * so this is genuinely memoized rather than a plain single pass per ball.
 * Time: O(rows * cols) | Space: O(rows * cols)
 */
class Solution {
    private int[][] grid;
    private int rows, cols;
    private Integer[][] dp;

    public int[] findBall(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.dp = new Integer[rows][cols];
        int[] result = new int[cols];
        for (int col = 0; col < cols; col++) {
            result[col] = finalColumn(0, col);
        }
        return result;
    }

    private int finalColumn(int row, int col) {
        if (row == rows) return col;
        if (dp[row][col] != null) return dp[row][col];
        int dir = grid[row][col];
        int nextCol = col + dir;
        int result;
        if (nextCol < 0 || nextCol >= cols || grid[row][nextCol] != dir) {
            result = -1; // wedged against a wall or the opposite-facing board edge
        } else {
            result = finalColumn(row + 1, nextCol);
        }
        dp[row][col] = result;
        return result;
    }
}
