/**
 * LeetCode 1312. Minimum Insertion Steps to Make a String Palindrome
 * Approach: Interval DP -- best(lo, hi) is the min insertions to make
 * s[lo..hi] a palindrome. Matching endpoints need no extra work beyond
 * the inner range; otherwise insert a copy of whichever endpoint is
 * cheaper to mirror and recurse on the correspondingly smaller range.
 * Time: O(n^2) | Space: O(n^2)
 */
class Solution {
    private String s;
    private Integer[][] dp;

    public int minInsertions(String s) {
        this.s = s;
        int n = s.length();
        this.dp = new Integer[n][n];
        return best(0, n - 1);
    }

    private int best(int lo, int hi) {
        if (lo >= hi) return 0;
        if (dp[lo][hi] != null) return dp[lo][hi];
        int result;
        if (s.charAt(lo) == s.charAt(hi)) {
            result = best(lo + 1, hi - 1);
        } else {
            result = 1 + Math.min(best(lo + 1, hi), best(lo, hi - 1));
        }
        dp[lo][hi] = result;
        return result;
    }
}
