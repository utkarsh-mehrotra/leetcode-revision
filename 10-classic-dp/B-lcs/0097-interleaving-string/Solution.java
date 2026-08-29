/**
 * LeetCode 97. Interleaving String
 * Approach: Top-down memoized recursion over (i, j) -- canForm(i, j) is
 * true if s1[i:] and s2[j:] can interleave to form s3[i+j:]. The next
 * character of s3 must come from s1 or s2's current position (or both,
 * tried independently).
 * Time: O(m * n) | Space: O(m * n)
 */
class Solution {
    private String s1, s2, s3;
    private Boolean[][] dp;

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.dp = new Boolean[s1.length() + 1][s2.length() + 1];
        return canForm(0, 0);
    }

    private boolean canForm(int i, int j) {
        if (i == s1.length() && j == s2.length()) return true;
        if (dp[i][j] != null) return dp[i][j];
        int k = i + j;
        boolean result = false;
        if (i < s1.length() && s1.charAt(i) == s3.charAt(k)) {
            result = canForm(i + 1, j);
        }
        if (!result && j < s2.length() && s2.charAt(j) == s3.charAt(k)) {
            result = canForm(i, j + 1);
        }
        dp[i][j] = result;
        return result;
    }
}
