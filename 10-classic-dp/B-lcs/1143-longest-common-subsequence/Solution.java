/**
 * LeetCode 1143. Longest Common Subsequence
 * Approach: Top-down memoized recursion over (i, j) -- matching leading
 * characters both join the subsequence and both pointers advance;
 * otherwise skip one character from whichever string gives the better result.
 * Time: O(m * n) | Space: O(m * n)
 */
class Solution {
    private String text1, text2;
    private Integer[][] dp;

    public int longestCommonSubsequence(String text1, String text2) {
        this.text1 = text1;
        this.text2 = text2;
        this.dp = new Integer[text1.length() + 1][text2.length() + 1];
        return solve(0, 0);
    }

    private int solve(int i, int j) {
        if (i == text1.length() || j == text2.length()) return 0;
        if (dp[i][j] != null) return dp[i][j];
        int result;
        if (text1.charAt(i) == text2.charAt(j)) {
            result = 1 + solve(i + 1, j + 1);
        } else {
            result = Math.max(solve(i + 1, j), solve(i, j + 1));
        }
        dp[i][j] = result;
        return result;
    }
}
