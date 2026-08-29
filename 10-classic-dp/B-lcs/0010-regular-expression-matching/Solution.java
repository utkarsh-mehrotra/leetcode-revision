/**
 * LeetCode 10. Regular Expression Matching
 * Approach: Top-down memoized recursion over (i, j) -- matches(i, j) is
 * true if s[i:] matches p[j:]. A '*' looks ahead two pattern characters:
 * it can match zero of the preceding element (skip both pattern chars) or,
 * if the preceding element matches s[i], consume one s character and
 * retry the same pattern position (allowing further repeats).
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

        boolean firstMatch = i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.');
        boolean result;
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            result = matches(i, j + 2) || (firstMatch && matches(i + 1, j));
        } else {
            result = firstMatch && matches(i + 1, j + 1);
        }
        dp[i][j] = result;
        return result;
    }
}
