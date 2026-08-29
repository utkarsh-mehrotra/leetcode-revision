/**
 * LeetCode 44. Wildcard Matching
 * Approach: Top-down memoized recursion over (i, j) -- matches(i, j) is
 * true if s[i:] matches p[j:]. '?' consumes exactly one character; '*'
 * either matches zero characters (skip it in the pattern) or one more
 * character of s while staying on the same '*' (allowing it to consume
 * an arbitrarily long run).
 * Time: O(s.length * p.length) | Space: O(s.length * p.length)
 */
class Solution {
    private String s, p;
    private Boolean[][] dp;

    public boolean isMatch(String s, String p) {
        this.s = s;
        this.p = p;
        this.dp = new Boolean[s.length() + 1][p.length() + 1];
        return matches(0, 0);
    }

    private boolean matches(int i, int j) {
        if (j == p.length()) return i == s.length();
        if (dp[i][j] != null) return dp[i][j];

        boolean result;
        char pc = p.charAt(j);
        if (pc == '*') {
            result = matches(i, j + 1) || (i < s.length() && matches(i + 1, j));
        } else {
            boolean charMatch = i < s.length() && (pc == s.charAt(i) || pc == '?');
            result = charMatch && matches(i + 1, j + 1);
        }
        dp[i][j] = result;
        return result;
    }
}
