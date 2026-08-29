/**
 * LeetCode 1092. Shortest Common Supersequence
 * Approach: Top-down memoized recursion computes the LCS length lcsLen(i,
 * j) for str1[i:] and str2[j:] (memoizing an int, not a string, keeps the
 * state space reasonable). The shortest supersequence is then
 * reconstructed by walking that same table from (0,0): matching
 * characters are shared, otherwise take whichever branch the LCS came
 * from and append that character, finishing with the untouched remainder
 * of either string.
 * Time: O(m * n) | Space: O(m * n)
 */
class Solution {
    private String str1, str2;
    private Integer[][] dp;

    public String shortestCommonSupersequence(String str1, String str2) {
        this.str1 = str1;
        this.str2 = str2;
        this.dp = new Integer[str1.length() + 1][str2.length() + 1];
        lcsLen(0, 0); // populate the table

        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0;
        while (i < str1.length() && j < str2.length()) {
            if (str1.charAt(i) == str2.charAt(j)) {
                sb.append(str1.charAt(i));
                i++;
                j++;
            } else if (lcsLen(i + 1, j) >= lcsLen(i, j + 1)) {
                sb.append(str1.charAt(i));
                i++;
            } else {
                sb.append(str2.charAt(j));
                j++;
            }
        }
        sb.append(str1.substring(i));
        sb.append(str2.substring(j));
        return sb.toString();
    }

    private int lcsLen(int i, int j) {
        if (i == str1.length() || j == str2.length()) return 0;
        if (dp[i][j] != null) return dp[i][j];
        int result;
        if (str1.charAt(i) == str2.charAt(j)) {
            result = 1 + lcsLen(i + 1, j + 1);
        } else {
            result = Math.max(lcsLen(i + 1, j), lcsLen(i, j + 1));
        }
        dp[i][j] = result;
        return result;
    }
}
