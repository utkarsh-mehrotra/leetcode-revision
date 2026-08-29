/**
 * LeetCode 304. Range Sum Query 2D - Immutable
 * Approach: Top-down memoized recursion -- prefixSum(r, c) is the sum of
 * the rectangle from (0,0) to (r-1,c-1), built via inclusion-exclusion
 * from the prefix sums one row up, one column left, and both.
 * Time: O(rows * cols) preprocessing, O(1) per query | Space: O(rows * cols)
 */
class NumMatrix {
    private int[][] matrix;
    private Integer[][] dp;

    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
        int rows = matrix.length, cols = matrix[0].length;
        this.dp = new Integer[rows + 1][cols + 1];
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return prefixSum(row2 + 1, col2 + 1) - prefixSum(row1, col2 + 1)
            - prefixSum(row2 + 1, col1) + prefixSum(row1, col1);
    }

    private int prefixSum(int r, int c) {
        if (r == 0 || c == 0) return 0;
        if (dp[r][c] != null) return dp[r][c];
        int result = matrix[r - 1][c - 1] + prefixSum(r - 1, c) + prefixSum(r, c - 1) - prefixSum(r - 1, c - 1);
        dp[r][c] = result;
        return result;
    }
}
