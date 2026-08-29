/**
 * LeetCode 115. Distinct Subsequences
 * Approach: Top-down memoized recursion over (i, j) -- ways(i, j) counts
 * distinct subsequences of s[i:] equal to t[j:]. s[i] can always be
 * skipped, and additionally consumed to match t[j] when they're equal.
 * Time: O(s.length * t.length) | Space: O(s.length * t.length)
 */
class Solution {
    private String s, t;
    private Integer[][] dp;

    public int numDistinct(String s, String t) {
        this.s = s;
        this.t = t;
        this.dp = new Integer[s.length() + 1][t.length() + 1];
        return ways(0, 0);
    }

    private int ways(int i, int j) {
        if (j == t.length()) return 1;
        if (i == s.length()) return 0;
        if (dp[i][j] != null) return dp[i][j];
        int result = ways(i + 1, j);
        if (s.charAt(i) == t.charAt(j)) {
            result += ways(i + 1, j + 1);
        }
        dp[i][j] = result;
        return result;
    }
}
