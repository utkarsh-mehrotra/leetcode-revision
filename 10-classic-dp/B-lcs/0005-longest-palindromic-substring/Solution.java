/**
 * LeetCode 5. Longest Palindromic Substring
 * Approach: Top-down memoized recursion -- isPalindrome(lo, hi) holds iff
 * the endpoints match and the strictly-inner substring is also a
 * palindrome. Every (lo, hi) pair is checked once and cached, tracking
 * the longest one found.
 * Time: O(n^2) | Space: O(n^2)
 */
class Solution {
    private String s;
    private Boolean[][] dp;

    public String longestPalindrome(String s) {
        this.s = s;
        int n = s.length();
        this.dp = new Boolean[n][n];
        int bestLo = 0, bestLen = 1;
        for (int lo = 0; lo < n; lo++) {
            for (int hi = lo; hi < n; hi++) {
                if (isPalindrome(lo, hi) && hi - lo + 1 > bestLen) {
                    bestLo = lo;
                    bestLen = hi - lo + 1;
                }
            }
        }
        return s.substring(bestLo, bestLo + bestLen);
    }

    private boolean isPalindrome(int lo, int hi) {
        if (lo >= hi) return true;
        if (dp[lo][hi] != null) return dp[lo][hi];
        boolean result = s.charAt(lo) == s.charAt(hi) && isPalindrome(lo + 1, hi - 1);
        dp[lo][hi] = result;
        return result;
    }
}
