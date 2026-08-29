/**
 * LeetCode 1320. Minimum Distance to Type a Word Using Two Fingers
 * Approach: Top-down memoized recursion over (i, idlePos) -- the "active"
 * finger is always implicitly at the position of word[i-1] (whichever
 * finger typed most recently); idlePos is where the other finger is
 * waiting (-1 if it has never moved). To type word[i], either move the
 * active finger there, or move the idle one there (swapping which finger
 * is idle going forward).
 * Time: O(n * 27) | Space: O(n * 27)
 */
class Solution {
    private String word;
    private Integer[][] dp;

    public int minimumDistance(String word) {
        this.word = word;
        this.dp = new Integer[word.length() + 1][27]; // idlePos offset by +1 (-1..25 -> 0..26)
        return solve(1, -1);
    }

    private int solve(int i, int idlePos) {
        if (i == word.length()) return 0;
        int key = idlePos + 1;
        if (dp[i][key] != null) return dp[i][key];
        int activePos = charIndex(word.charAt(i - 1));
        int targetPos = charIndex(word.charAt(i));

        int moveActive = dist(activePos, targetPos) + solve(i + 1, idlePos);
        int idleCost = (idlePos == -1) ? 0 : dist(idlePos, targetPos);
        int moveIdle = idleCost + solve(i + 1, activePos);

        int result = Math.min(moveActive, moveIdle);
        dp[i][key] = result;
        return result;
    }

    private int charIndex(char c) {
        return c - 'A';
    }

    // Manhattan distance between two letters laid out on a 5-wide, 6-row keyboard grid.
    private int dist(int a, int b) {
        int r1 = a / 6, c1 = a % 6;
        int r2 = b / 6, c2 = b % 6;
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}
