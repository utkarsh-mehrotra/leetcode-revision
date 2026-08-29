/**
 * LeetCode 1240. Tiling a Rectangle with the Fewest Squares
 * Approach: Not a classic overlapping-subproblem memoization -- the
 * skyline state space (one fill-height per column) is too large to
 * usefully cache at n,m <= 13, so this is branch-and-bound backtracking:
 * always fill the leftmost column at the current minimum height, try the
 * largest square that fits there first (for fast early pruning), and
 * prune any branch whose square count already reaches the best found so far.
 * Time: exponential worst case, bounded in practice by n,m <= 13 and pruning
 * Space: O(m) recursion state
 */
class Solution {
    private int rows, cols;
    private int best;

    public int tilingRectangle(int n, int m) {
        this.rows = n;
        this.cols = m;
        this.best = n * m; // trivial upper bound: all 1x1 squares
        backtrack(new int[m], 0);
        return best;
    }

    private void backtrack(int[] heights, int count) {
        if (count >= best) return;
        int minHeight = Integer.MAX_VALUE, col = -1;
        for (int i = 0; i < cols; i++) {
            if (heights[i] < minHeight) {
                minHeight = heights[i];
                col = i;
            }
        }
        if (minHeight == rows) {
            best = count;
            return;
        }

        int maxWidth = 0;
        while (col + maxWidth < cols && heights[col + maxWidth] == minHeight) maxWidth++;
        int maxSize = Math.min(rows - minHeight, maxWidth);

        for (int size = maxSize; size >= 1; size--) {
            for (int i = col; i < col + size; i++) heights[i] += size;
            backtrack(heights, count + 1);
            for (int i = col; i < col + size; i++) heights[i] -= size;
        }
    }
}
