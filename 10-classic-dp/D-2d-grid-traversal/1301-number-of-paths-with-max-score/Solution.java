import java.util.List;

/**
 * LeetCode 1301. Number of Paths with Max Score
 * Approach: Top-down memoized recursion from the bottom-right 'S' toward
 * 'E' -- solve(r, c) returns {bestSum, waysToAchieveIt} using the best of
 * the (up to 3) neighboring cells one step closer to the start, summing
 * ways across every neighbor that ties the best sum.
 * Time: O(n^2) | Space: O(n^2)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private String[] board;
    private int n;
    private int[][][] dp; // dp[r][c] = {bestSum, ways}; null = not yet computed

    public int[] pathsWithMaxScore(List<String> board) {
        this.board = board.toArray(new String[0]);
        this.n = this.board.length;
        this.dp = new int[n][n][];
        int[] result = solve(n - 1, n - 1);
        if (result[0] < 0) return new int[]{0, 0};
        return new int[]{result[0], result[1]};
    }

    private int[] solve(int r, int c) {
        if (r < 0 || c < 0 || board[r].charAt(c) == 'X') return new int[]{-1, 0};
        if (dp[r][c] != null) return dp[r][c];
        if (r == 0 && c == 0) {
            dp[r][c] = new int[]{0, 1};
            return dp[r][c];
        }

        int bestSum = -1, ways = 0;
        int[][] neighbors = {solve(r - 1, c), solve(r, c - 1), solve(r - 1, c - 1)};
        for (int[] nb : neighbors) {
            if (nb[0] < 0) continue;
            if (nb[0] > bestSum) {
                bestSum = nb[0];
                ways = nb[1];
            } else if (nb[0] == bestSum) {
                ways = (ways + nb[1]) % MOD;
            }
        }
        if (bestSum < 0) {
            dp[r][c] = new int[]{-1, 0};
            return dp[r][c];
        }
        int cellValue = (r == n - 1 && c == n - 1) ? 0 : (board[r].charAt(c) - '0');
        dp[r][c] = new int[]{bestSum + cellValue, ways};
        return dp[r][c];
    }
}
