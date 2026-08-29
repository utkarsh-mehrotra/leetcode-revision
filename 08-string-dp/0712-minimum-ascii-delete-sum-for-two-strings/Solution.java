/**
 * LeetCode 712. Minimum ASCII Delete Sum for Two Strings
 * Approach: Top-down memoized recursion over (i, j) -- if the current
 * characters match, keep both and recurse; otherwise delete whichever
 * single character (from either string) yields the cheaper outcome. Once
 * one string is exhausted, every remaining character of the other must
 * be deleted.
 * Time: O(len1 * len2) | Space: O(len1 * len2)
 */
class Solution {
    private String s1, s2;
    private Integer[][] dp;

    public int minimumDeleteSum(String s1, String s2) {
        this.s1 = s1;
        this.s2 = s2;
        this.dp = new Integer[s1.length() + 1][s2.length() + 1];
        return solve(0, 0);
    }

    private int solve(int i, int j) {
        if (i == s1.length()) return remainingSum(s2, j);
        if (j == s2.length()) return remainingSum(s1, i);
        if (dp[i][j] != null) return dp[i][j];
        int result;
        if (s1.charAt(i) == s2.charAt(j)) {
            result = solve(i + 1, j + 1);
        } else {
            result = Math.min(s1.charAt(i) + solve(i + 1, j), s2.charAt(j) + solve(i, j + 1));
        }
        dp[i][j] = result;
        return result;
    }

    private int remainingSum(String s, int from) {
        int sum = 0;
        for (int k = from; k < s.length(); k++) sum += s.charAt(k);
        return sum;
    }
}
