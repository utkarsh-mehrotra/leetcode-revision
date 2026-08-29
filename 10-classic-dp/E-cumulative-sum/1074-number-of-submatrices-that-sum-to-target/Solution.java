import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1074. Number of Submatrices That Sum to Target
 * Approach: For every pair of row boundaries, collapse the matrix to a 1D
 * column-sum array (each sum computed in O(1) via a memoized 2D prefix
 * sum) and count subarrays summing to target with a running prefix sum
 * against a hashmap of prefix-sum frequencies (the standard "subarray sum
 * equals K" technique). The row-pair loop itself has no overlapping
 * subproblem to cache.
 * Time: O(rows^2 * cols) | Space: O(rows * cols + cols)
 */
class Solution {
    private int[][] matrix;
    private Integer[][] dp;

    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        this.matrix = matrix;
        int rows = matrix.length, cols = matrix[0].length;
        this.dp = new Integer[rows + 1][cols + 1];

        int count = 0;
        for (int r1 = 0; r1 < rows; r1++) {
            for (int r2 = r1; r2 < rows; r2++) {
                Map<Integer, Integer> freq = new HashMap<>();
                freq.put(0, 1);
                int prefix = 0;
                for (int c = 0; c < cols; c++) {
                    int colSum = prefixSum(r2 + 1, c + 1) - prefixSum(r1, c + 1)
                        - prefixSum(r2 + 1, c) + prefixSum(r1, c);
                    prefix += colSum;
                    count += freq.getOrDefault(prefix - target, 0);
                    freq.merge(prefix, 1, Integer::sum);
                }
            }
        }
        return count;
    }

    private int prefixSum(int r, int c) {
        if (r == 0 || c == 0) return 0;
        if (dp[r][c] != null) return dp[r][c];
        int result = matrix[r - 1][c - 1] + prefixSum(r - 1, c) + prefixSum(r, c - 1) - prefixSum(r - 1, c - 1);
        dp[r][c] = result;
        return result;
    }
}
