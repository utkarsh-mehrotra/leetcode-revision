/**
 * LeetCode 392. Is Subsequence
 * Approach: Top-down memoized recursion over (i, j) -- can s[i:] be
 * matched as a subsequence within t[j:]? On a character match, both
 * pointers advance; otherwise only t's pointer advances.
 * Time: O(s.length * t.length) | Space: O(s.length * t.length)
 */
class Solution {
    private String s, t;
    private Boolean[][] dp;

    public boolean isSubsequence(String s, String t) {
        this.s = s;
        this.t = t;
        this.dp = new Boolean[s.length() + 1][t.length() + 1];
        return solve(0, 0);
    }

    private boolean solve(int i, int j) {
        if (i == s.length()) return true;
        if (j == t.length()) return false;
        if (dp[i][j] != null) return dp[i][j];
        boolean result = (s.charAt(i) == t.charAt(j)) ? solve(i + 1, j + 1) : solve(i, j + 1);
        dp[i][j] = result;
        return result;
    }
}
