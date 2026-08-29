/**
 * LeetCode 799. Champagne Tower
 * Approach: Top-down memoized recursion -- poured(row, col) is the amount
 * of champagne that has flowed into that glass, fed equally by the
 * overflow (amount past its 1-cup capacity) of the two glasses above it.
 * A glass holds at most 1 cup, so the answer clamps poured(...) to 1.
 * Time: O(row^2) | Space: O(row^2)
 */
class Solution {
    private double poureCups;
    private Double[][] dp;

    public double champagneTower(int poured, int queryRow, int queryGlass) {
        this.poureCups = poured;
        this.dp = new Double[queryRow + 1][queryRow + 1];
        return Math.min(1.0, poured(queryRow, queryGlass));
    }

    private double poured(int row, int col) {
        if (col < 0 || col > row) return 0;
        if (row == 0) return poureCups;
        if (dp[row][col] != null) return dp[row][col];
        double fromLeft = overflow(row - 1, col - 1);
        double fromRight = overflow(row - 1, col);
        double result = (fromLeft + fromRight) / 2.0;
        dp[row][col] = result;
        return result;
    }

    private double overflow(int row, int col) {
        return Math.max(0, poured(row, col) - 1.0);
    }
}
