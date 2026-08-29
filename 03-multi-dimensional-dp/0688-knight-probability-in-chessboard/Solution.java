/**
 * LeetCode 688. Knight Probability in Chessboard
 * Approach: Top-down memoized recursion over (row, col, movesLeft) -- the
 * probability of remaining on the board is the average, over all 8 knight
 * moves, of the probability from the landing square (0 if it falls off).
 * Time: O(n^2 * k) | Space: O(n^2 * k)
 */
class Solution {
    private static final int[][] MOVES = {
        {-2, -1}, {-2, 1}, {2, -1}, {2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}
    };
    private int n;
    private Double[][][] dp;

    public double knightProbability(int n, int k, int row, int column) {
        this.n = n;
        this.dp = new Double[n][n][k + 1];
        return prob(row, column, k);
    }

    private double prob(int row, int col, int movesLeft) {
        if (movesLeft == 0) return 1.0;
        if (dp[row][col][movesLeft] != null) return dp[row][col][movesLeft];
        double total = 0;
        for (int[] move : MOVES) {
            int nr = row + move[0], nc = col + move[1];
            if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                total += prob(nr, nc, movesLeft - 1);
            }
        }
        double result = total / 8.0;
        dp[row][col][movesLeft] = result;
        return result;
    }
}
