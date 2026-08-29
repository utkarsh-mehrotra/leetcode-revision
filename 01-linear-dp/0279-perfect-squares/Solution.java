/**
 * LeetCode 279. Perfect Squares
 * Approach: Top-down memoized recursion -- numSquares(n) = 1 + the best of
 * numSquares(n - j*j) over every square j*j <= n.
 * Time: O(n * sqrt(n)) | Space: O(n)
 */
class Solution {
    private Integer[] memo;

    public int numSquares(int n) {
        memo = new Integer[n + 1];
        return solve(n);
    }

    private int solve(int n) {
        if (n == 0) return 0;
        if (memo[n] != null) return memo[n];
        int best = Integer.MAX_VALUE;
        for (int j = 1; j * j <= n; j++) {
            best = Math.min(best, 1 + solve(n - j * j));
        }
        memo[n] = best;
        return best;
    }
}
