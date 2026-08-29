/**
 * LeetCode 741. Cherry Pickup
 * Approach: Model two people walking from (0,0) to (n-1,n-1) simultaneously
 * (equivalent to one round trip) so both take the same number of steps;
 * with r1+c1 == r2+c2 always, c2 is implied by (r1, c1, r2). Top-down
 * memoized recursion over (r1, c1, r2) tries all 4 combinations of each
 * person moving right or down, collecting a cell's cherry once even if
 * both people land on it.
 * Time: O(n^3) | Space: O(n^3)
 */
class Solution {
    private int[][] grid;
    private int n;
    private Integer[][][] dp;
    private static final int INVALID = Integer.MIN_VALUE / 2;

    public int cherryPickup(int[][] grid) {
        this.grid = grid;
        this.n = grid.length;
        this.dp = new Integer[n][n][n];
        int result = solve(0, 0, 0);
        return Math.max(0, result);
    }

    private int solve(int r1, int c1, int r2) {
        int c2 = r1 + c1 - r2;
        if (r1 >= n || c1 >= n || r2 >= n || c2 >= n) return INVALID;
        if (grid[r1][c1] == -1 || grid[r2][c2] == -1) return INVALID;
        if (r1 == n - 1 && c1 == n - 1) return grid[r1][c1];
        if (dp[r1][c1][r2] != null) return dp[r1][c1][r2];

        int cherries = grid[r1][c1] + (r1 == r2 && c1 == c2 ? 0 : grid[r2][c2]);
        int best = Math.max(
            Math.max(solve(r1 + 1, c1, r2 + 1), solve(r1 + 1, c1, r2)),
            Math.max(solve(r1, c1 + 1, r2 + 1), solve(r1, c1 + 1, r2))
        );
        int result = (best == INVALID) ? INVALID : cherries + best;
        dp[r1][c1][r2] = result;
        return result;
    }
}
