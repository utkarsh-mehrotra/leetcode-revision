/**
 * LeetCode 516. Longest Palindromic Subsequence
 * Approach: Interval DP -- best(lo, hi) is the longest palindromic
 * subsequence in s[lo..hi]. Matching endpoints both join the answer
 * around the inner best; otherwise drop whichever endpoint and keep the
 * better result.
 * Time: O(n^2) | Space: O(n^2)
 */
class Solution {
    private String s;
    private Integer[][] dp;

    public int longestPalindromeSubseq(String s) {
        this.s = s;
        int n = s.length();
        this.dp = new Integer[n][n];
        return best(0, n - 1);
    }

    private int best(int lo, int hi) {
        if (lo > hi) return 0;
        if (lo == hi) return 1;
        if (dp[lo][hi] != null) return dp[lo][hi];
        int result;
        if (s.charAt(lo) == s.charAt(hi)) {
            result = 2 + best(lo + 1, hi - 1);
        } else {
            result = Math.max(best(lo + 1, hi), best(lo, hi - 1));
        }
        dp[lo][hi] = result;
        return result;
    }
}
