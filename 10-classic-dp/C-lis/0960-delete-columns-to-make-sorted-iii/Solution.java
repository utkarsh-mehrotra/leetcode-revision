/**
 * LeetCode 960. Delete Columns to Make Sorted III
 * Approach: Top-down memoized recursion (LIS-style over columns) --
 * keptEndingAt(i) is the max number of columns keepable, ending with
 * column i, such that every row reads non-decreasing across the kept
 * columns. Column j (j < i) can precede column i only if every row's
 * character at j is <= its character at i.
 * Time: O(cols^2 * rows) | Space: O(cols)
 */
class Solution {
    private String[] strs;
    private int cols;
    private Integer[] dp;

    public int minDeletionSize(String[] strs) {
        this.strs = strs;
        this.cols = strs[0].length();
        this.dp = new Integer[cols];
        int best = 0;
        for (int i = 0; i < cols; i++) {
            best = Math.max(best, keptEndingAt(i));
        }
        return cols - best;
    }

    private int keptEndingAt(int i) {
        if (dp[i] != null) return dp[i];
        int best = 1;
        for (int j = 0; j < i; j++) {
            if (columnLeq(j, i)) {
                best = Math.max(best, keptEndingAt(j) + 1);
            }
        }
        dp[i] = best;
        return best;
    }

    private boolean columnLeq(int j, int i) {
        for (String row : strs) {
            if (row.charAt(j) > row.charAt(i)) return false;
        }
        return true;
    }
}
