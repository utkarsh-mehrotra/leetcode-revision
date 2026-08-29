import java.util.List;

/**
 * LeetCode 120. Triangle
 * Approach: Top-down memoized recursion -- best(row, col) is the minimum
 * path sum from this cell to the base, built from the cheaper of the two
 * cells reachable in the row below.
 * Time: O(n^2) | Space: O(n^2)
 */
class Solution {
    private List<List<Integer>> triangle;
    private Integer[][] dp;

    public int minimumTotal(List<List<Integer>> triangle) {
        this.triangle = triangle;
        int n = triangle.size();
        this.dp = new Integer[n][n];
        return best(0, 0);
    }

    private int best(int row, int col) {
        if (row == triangle.size()) return 0;
        if (dp[row][col] != null) return dp[row][col];
        int result = triangle.get(row).get(col) + Math.min(best(row + 1, col), best(row + 1, col + 1));
        dp[row][col] = result;
        return result;
    }
}
