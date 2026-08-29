/**
 * LeetCode 664. Strange Printer
 * Approach: Interval DP -- best(lo, hi) is the min turns to print s[lo..hi].
 * Printing s[hi] on its own turn costs best(lo, hi-1) + 1; but if some
 * earlier s[k] == s[hi], that turn's brush stroke can be stretched to also
 * cover position hi for free, merging the two sides.
 * Time: O(n^3) | Space: O(n^2)
 */
class Solution {
    private String s;
    private Integer[][] dp;

    public int strangePrinter(String s) {
        this.s = s;
        int n = s.length();
        this.dp = new Integer[n][n];
        return best(0, n - 1);
    }

    private int best(int lo, int hi) {
        if (lo > hi) return 0;
        if (lo == hi) return 1;
        if (dp[lo][hi] != null) return dp[lo][hi];
        int result = best(lo, hi - 1) + 1;
        for (int k = lo; k < hi; k++) {
            if (s.charAt(k) == s.charAt(hi)) {
                result = Math.min(result, best(lo, k) + best(k + 1, hi - 1));
            }
        }
        dp[lo][hi] = result;
        return result;
    }
}
