import java.util.TreeSet;

/**
 * LeetCode 363. Max Sum of Rectangle No Larger Than K
 * Approach: Not a DP recursion -- for every pair of column boundaries,
 * collapse the matrix to a 1D row-sum array (via a memoized prefix-sum
 * recursion) and find its best subarray sum <= K using a running prefix
 * sum against a TreeSet (find the smallest earlier prefix >= currentPrefix
 * - K). The column-pair loop itself has no overlapping subproblem to cache.
 * Time: O(min(rows,cols)^2 * max(rows,cols) * log(max(rows,cols))) | Space: O(rows * cols)
 */
class Solution {
    private int[][] matrix;
    private int rows, cols;
    private Integer[][] dp;

    public int maxSumSubmatrix(int[][] matrix, int k) {
        this.matrix = matrix;
        this.rows = matrix.length;
        this.cols = matrix[0].length;
        this.dp = new Integer[rows + 1][cols + 1];

        int best = Integer.MIN_VALUE;
        for (int c1 = 0; c1 < cols; c1++) {
            for (int c2 = c1; c2 < cols; c2++) {
                TreeSet<Integer> prefixes = new TreeSet<>();
                prefixes.add(0);
                int prefix = 0;
                for (int r = 0; r < rows; r++) {
                    int rowSum = prefixSum(r + 1, c2 + 1) - prefixSum(r, c2 + 1)
                        - prefixSum(r + 1, c1) + prefixSum(r, c1);
                    prefix += rowSum;
                    Integer ceiling = prefixes.ceiling(prefix - k);
                    if (ceiling != null) {
                        best = Math.max(best, prefix - ceiling);
                    }
                    prefixes.add(prefix);
                }
            }
        }
        return best;
    }

    private int prefixSum(int r, int c) {
        if (r == 0 || c == 0) return 0;
        if (dp[r][c] != null) return dp[r][c];
        int result = matrix[r - 1][c - 1] + prefixSum(r - 1, c) + prefixSum(r, c - 1) - prefixSum(r - 1, c - 1);
        dp[r][c] = result;
        return result;
    }
}
