/**
 * LeetCode 1639. Number of Ways to Form Target String Given a Dictionary
 * Approach: All words share the same length; precompute how many words
 * have each letter at each column. Top-down memoized recursion over
 * (targetIndex, column) -- skip this column entirely, or use it to supply
 * target[targetIndex] (weighted by how many words offer that letter
 * there) and advance both indices; each column is usable at most once,
 * strictly left to right.
 * Time: O(target.length * wordLen) | Space: O(target.length * wordLen)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private String target;
    private int wordLen;
    private int[][] charCountAtColumn;
    private Long[][] dp;

    public int numWays(String[] words, String target) {
        this.target = target;
        this.wordLen = words[0].length();
        charCountAtColumn = new int[wordLen][26];
        for (String word : words) {
            for (int col = 0; col < wordLen; col++) {
                charCountAtColumn[col][word.charAt(col) - 'a']++;
            }
        }
        dp = new Long[target.length() + 1][wordLen + 1];
        return (int) solve(0, 0);
    }

    private long solve(int ti, int col) {
        if (ti == target.length()) return 1;
        if (col == wordLen) return 0;
        if (dp[ti][col] != null) return dp[ti][col];
        long skip = solve(ti, col + 1);
        long use = (long) charCountAtColumn[col][target.charAt(ti) - 'a'] * solve(ti + 1, col + 1) % MOD;
        long result = (skip + use) % MOD;
        dp[ti][col] = result;
        return result;
    }
}
