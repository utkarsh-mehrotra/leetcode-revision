/**
 * LeetCode 1594. Maximum Non-negative Product in a Matrix
 * Approach: Top-down memoized recursion -- because negative cells can
 * flip a running product's sign, best(r, c) tracks both the max and min
 * product achievable on a path from (0,0) to (r,c), each built from
 * whichever predecessor (above or left) yields the better result once
 * multiplied by grid[r][c].
 * Time: O(m * n) | Space: O(m * n)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private int[][] grid;
    private Long[][][] dp; // dp[r][c] = {max, min}

    public int maxProductPath(int[][] grid) {
        this.grid = grid;
        int m = grid.length, n = grid[0].length;
        this.dp = new Long[m][n][];
        long best = best(m - 1, n - 1)[0];
        return best < 0 ? -1 : (int) (best % MOD);
    }

    private Long[] best(int r, int c) {
        if (dp[r][c] != null) return dp[r][c];
        long val = grid[r][c];
        long max, min;
        if (r == 0 && c == 0) {
            max = val;
            min = val;
        } else {
            long candMax = Long.MIN_VALUE, candMin = Long.MAX_VALUE;
            if (r > 0) {
                Long[] up = best(r - 1, c);
                candMax = Math.max(candMax, Math.max(val * up[0], val * up[1]));
                candMin = Math.min(candMin, Math.min(val * up[0], val * up[1]));
            }
            if (c > 0) {
                Long[] left = best(r, c - 1);
                candMax = Math.max(candMax, Math.max(val * left[0], val * left[1]));
                candMin = Math.min(candMin, Math.min(val * left[0], val * left[1]));
            }
            max = candMax;
            min = candMin;
        }
        Long[] result = {max, min};
        dp[r][c] = result;
        return result;
    }
}
