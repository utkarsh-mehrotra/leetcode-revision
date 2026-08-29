/**
 * LeetCode 174. Dungeon Game
 * Approach: Top-down memoized recursion from the princess's cell backward
 * -- minHpNeeded(r, c) is the least HP required upon ENTERING (r,c) to
 * survive the rest of the path (HP must stay >= 1 at every step). It's
 * whatever the cheaper of the two neighbors requires, offset by this
 * cell's own value, floored at 1.
 * Time: O(m * n) | Space: O(m * n)
 */
class Solution {
    private int[][] dungeon;
    private int rows, cols;
    private Integer[][] dp;

    public int calculateMinimumHP(int[][] dungeon) {
        this.dungeon = dungeon;
        this.rows = dungeon.length;
        this.cols = dungeon[0].length;
        this.dp = new Integer[rows][cols];
        return minHpNeeded(0, 0);
    }

    private int minHpNeeded(int r, int c) {
        if (r == rows || c == cols) return Integer.MAX_VALUE / 2;
        if (dp[r][c] != null) return dp[r][c];
        int needAfter;
        if (r == rows - 1 && c == cols - 1) {
            needAfter = 1;
        } else {
            needAfter = Math.min(minHpNeeded(r + 1, c), minHpNeeded(r, c + 1));
        }
        int result = Math.max(1, needAfter - dungeon[r][c]);
        dp[r][c] = result;
        return result;
    }
}
