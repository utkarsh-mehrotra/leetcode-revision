/**
 * LeetCode 72. Edit Distance
 * Approach: Top-down memoized recursion over (i, j) -- solve(i, j) is the
 * min edits to turn word1[i:] into word2[j:]. Matching characters need no
 * edit; otherwise try insert, delete, and replace and take the cheapest.
 * Time: O(m * n) | Space: O(m * n)
 */
class Solution {
    private String word1, word2;
    private Integer[][] dp;

    public int minDistance(String word1, String word2) {
        this.word1 = word1;
        this.word2 = word2;
        this.dp = new Integer[word1.length() + 1][word2.length() + 1];
        return solve(0, 0);
    }

    private int solve(int i, int j) {
        if (i == word1.length()) return word2.length() - j;
        if (j == word2.length()) return word1.length() - i;
        if (dp[i][j] != null) return dp[i][j];
        int result;
        if (word1.charAt(i) == word2.charAt(j)) {
            result = solve(i + 1, j + 1);
        } else {
            int insert = solve(i, j + 1);
            int delete = solve(i + 1, j);
            int replace = solve(i + 1, j + 1);
            result = 1 + Math.min(insert, Math.min(delete, replace));
        }
        dp[i][j] = result;
        return result;
    }
}
