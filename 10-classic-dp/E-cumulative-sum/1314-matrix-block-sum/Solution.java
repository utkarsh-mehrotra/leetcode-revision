/**
 * LeetCode 1314. Matrix Block Sum
 * Approach: Top-down memoized recursion builds a 2D prefix-sum table (via
 * inclusion-exclusion), then every block sum is an O(1) rectangle query
 * against it, clamped to the matrix bounds.
 * Time: O(rows * cols) | Space: O(rows * cols)
 */
class Solution {
    private int[][] mat;
    private int rows, cols;
    private Integer[][] dp;

    public int[][] matrixBlockSum(int[][] mat, int k) {
        this.mat = mat;
        rows = mat.length;
        cols = mat[0].length;
        dp = new Integer[rows + 1][cols + 1];

        int[][] answer = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int r1 = Math.max(0, r - k), c1 = Math.max(0, c - k);
                int r2 = Math.min(rows - 1, r + k), c2 = Math.min(cols - 1, c + k);
                answer[r][c] = prefixSum(r2 + 1, c2 + 1) - prefixSum(r1, c2 + 1)
                    - prefixSum(r2 + 1, c1) + prefixSum(r1, c1);
            }
        }
        return answer;
    }

    private int prefixSum(int r, int c) {
        if (r == 0 || c == 0) return 0;
        if (dp[r][c] != null) return dp[r][c];
        int result = mat[r - 1][c - 1] + prefixSum(r - 1, c) + prefixSum(r, c - 1) - prefixSum(r - 1, c - 1);
        dp[r][c] = result;
        return result;
    }
}
