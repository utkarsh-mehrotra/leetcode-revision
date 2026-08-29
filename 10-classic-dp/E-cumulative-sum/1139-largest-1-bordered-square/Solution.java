/**
 * LeetCode 1139. Largest 1-Bordered Square
 * Approach: Top-down memoized recursion computes, for every cell, the run
 * of consecutive 1's reaching it from the left and from above. For a
 * candidate square of side k with bottom-right corner (r,c), the border
 * is intact iff the top-right and bottom-left corners each have a left-
 * run/up-run of at least k (their own two sides of the square), checked
 * for decreasing k until one fits.
 * Time: O(n^3) worst case | Space: O(n^2)
 */
class Solution {
    private int[][] grid;
    private int rows, cols;
    private Integer[][] leftDp, upDp;

    public int largest1BorderedSquare(int[][] grid) {
        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;
        leftDp = new Integer[rows][cols];
        upDp = new Integer[rows][cols];

        int best = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 0) continue;
                int maxSide = Math.min(left(r, c), up(r, c));
                for (int side = maxSide; side > best; side--) {
                    int tr = r - side + 1, tc = c - side + 1;
                    if (tr < 0 || tc < 0) continue;
                    if (left(tr, c) >= side && up(r, tc) >= side) {
                        best = side;
                        break;
                    }
                }
            }
        }
        return best * best;
    }

    private int left(int r, int c) {
        if (grid[r][c] == 0) return 0;
        if (leftDp[r][c] != null) return leftDp[r][c];
        int result = 1 + (c > 0 ? left(r, c - 1) : 0);
        leftDp[r][c] = result;
        return result;
    }

    private int up(int r, int c) {
        if (grid[r][c] == 0) return 0;
        if (upDp[r][c] != null) return upDp[r][c];
        int result = 1 + (r > 0 ? up(r - 1, c) : 0);
        upDp[r][c] = result;
        return result;
    }
}
