/**
 * LeetCode 1444. Number of Ways of Cutting a Pizza
 * Approach: Top-down memoized recursion over (row, col, cutsLeft) -- with
 * no cuts left, the remaining sub-pizza is valid iff it still has an
 * apple; otherwise try every horizontal or vertical cut whose top/left
 * piece contains at least one apple (checked in O(1) via an apple prefix
 * sum), recursing on the remaining sub-pizza with one fewer cut.
 * Time: O(rows * cols * k * (rows + cols)) | Space: O(rows * cols * k)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private int rows, cols;
    private int[][] appleCount; // appleCount[r][c] = apples in [r..rows) x [c..cols)
    private Integer[][][] dp;

    public int ways(String[] pizza, int k) {
        rows = pizza.length;
        cols = pizza[0].length();
        appleCount = new int[rows + 1][cols + 1];
        for (int r = rows - 1; r >= 0; r--) {
            for (int c = cols - 1; c >= 0; c--) {
                int has = pizza[r].charAt(c) == 'A' ? 1 : 0;
                appleCount[r][c] = has + appleCount[r + 1][c] + appleCount[r][c + 1] - appleCount[r + 1][c + 1];
            }
        }
        dp = new Integer[rows][cols][k];
        return solve(0, 0, k - 1);
    }

    private boolean hasApple(int r1, int c1, int r2, int c2) { // region [r1..r2) x [c1..c2)
        return appleCount[r1][c1] - appleCount[r2][c1] - appleCount[r1][c2] + appleCount[r2][c2] > 0;
    }

    private int solve(int row, int col, int cutsLeft) {
        if (cutsLeft == 0) return hasApple(row, col, rows, cols) ? 1 : 0;
        if (dp[row][col][cutsLeft] != null) return dp[row][col][cutsLeft];
        long total = 0;
        for (int r = row + 1; r < rows; r++) { // horizontal cut, keep [row..r) as this piece
            if (hasApple(row, col, r, cols)) {
                total = (total + solve(r, col, cutsLeft - 1)) % MOD;
            }
        }
        for (int c = col + 1; c < cols; c++) { // vertical cut, keep [col..c) as this piece
            if (hasApple(row, col, rows, c)) {
                total = (total + solve(row, c, cutsLeft - 1)) % MOD;
            }
        }
        dp[row][col][cutsLeft] = (int) total;
        return (int) total;
    }
}
