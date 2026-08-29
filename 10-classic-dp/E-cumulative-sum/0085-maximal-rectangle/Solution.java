import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 85. Maximal Rectangle
 * Approach: Top-down memoized recursion builds each column's "height"
 * (run of consecutive 1's upward) per row. Turning each row into a
 * histogram this way, the largest rectangle in each histogram is found
 * with a monotonic stack (the standard largest-rectangle-in-histogram
 * technique -- not a DP recursion itself, since it's driven by a stack
 * invariant rather than reusable overlapping subproblems).
 * Time: O(rows * cols) | Space: O(rows * cols)
 */
class Solution {
    private char[][] grid;
    private Integer[][] dp;

    public int maximalRectangle(char[][] matrix) {
        this.grid = matrix;
        int rows = matrix.length, cols = matrix[0].length;
        this.dp = new Integer[rows][cols];

        int best = 0;
        int[] heights = new int[cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                heights[c] = heightAt(r, c);
            }
            best = Math.max(best, largestRectangleArea(heights));
        }
        return best;
    }

    private int heightAt(int r, int c) {
        if (grid[r][c] == '0') return 0;
        if (dp[r][c] != null) return dp[r][c];
        int result = 1 + (r > 0 ? heightAt(r - 1, c) : 0);
        dp[r][c] = result;
        return result;
    }

    private int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int best = 0;
        for (int i = 0; i <= heights.length; i++) {
            int h = (i == heights.length) ? 0 : heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] >= h) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                best = Math.max(best, height * width);
            }
            stack.push(i);
        }
        return best;
    }
}
