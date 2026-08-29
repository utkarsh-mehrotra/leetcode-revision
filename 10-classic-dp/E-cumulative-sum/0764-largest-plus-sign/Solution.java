/**
 * LeetCode 764. Largest Plus Sign
 * Approach: Top-down memoized recursion computes, for every cell, the run
 * of consecutive 1's reaching it from each of the 4 directions. A plus of
 * arm-length k centered at a cell needs all 4 runs to be >= k, so each
 * cell's plus order is the min of its 4 directional runs.
 * Time: O(n^2) | Space: O(n^2)
 */
class Solution {
    private boolean[][] blocked;
    private int n;
    private Integer[][] leftDp, rightDp, upDp, downDp;

    public int orderOfLargestPlusSign(int n, int[][] mines) {
        this.n = n;
        blocked = new boolean[n][n];
        for (int[] mine : mines) blocked[mine[0]][mine[1]] = true;
        leftDp = new Integer[n][n];
        rightDp = new Integer[n][n];
        upDp = new Integer[n][n];
        downDp = new Integer[n][n];

        int best = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int order = Math.min(Math.min(left(r, c), right(r, c)), Math.min(up(r, c), down(r, c)));
                best = Math.max(best, order);
            }
        }
        return best;
    }

    private int left(int r, int c) {
        if (blocked[r][c]) return 0;
        if (leftDp[r][c] != null) return leftDp[r][c];
        int result = 1 + (c > 0 ? left(r, c - 1) : 0);
        leftDp[r][c] = result;
        return result;
    }

    private int right(int r, int c) {
        if (blocked[r][c]) return 0;
        if (rightDp[r][c] != null) return rightDp[r][c];
        int result = 1 + (c < n - 1 ? right(r, c + 1) : 0);
        rightDp[r][c] = result;
        return result;
    }

    private int up(int r, int c) {
        if (blocked[r][c]) return 0;
        if (upDp[r][c] != null) return upDp[r][c];
        int result = 1 + (r > 0 ? up(r - 1, c) : 0);
        upDp[r][c] = result;
        return result;
    }

    private int down(int r, int c) {
        if (blocked[r][c]) return 0;
        if (downDp[r][c] != null) return downDp[r][c];
        int result = 1 + (r < n - 1 ? down(r + 1, c) : 0);
        downDp[r][c] = result;
        return result;
    }
}
