/**
 * LeetCode 647. Palindromic Substrings
 * Approach: Top-down memoized recursion -- isPalindrome(lo, hi) holds iff
 * the endpoints match and the strictly-inner substring is also a
 * palindrome. Every (lo, hi) pair with lo <= hi is checked once and its
 * result cached, then counted.
 * Time: O(n^2) | Space: O(n^2)
 */
class Solution {
    private String s;
    private Boolean[][] dp;

    public int countSubstrings(String s) {
        this.s = s;
        int n = s.length();
        this.dp = new Boolean[n][n];
        int count = 0;
        for (int lo = 0; lo < n; lo++) {
            for (int hi = lo; hi < n; hi++) {
                if (isPalindrome(lo, hi)) count++;
            }
        }
        return count;
    }

    private boolean isPalindrome(int lo, int hi) {
        if (lo >= hi) return true;
        if (dp[lo][hi] != null) return dp[lo][hi];
        boolean result = s.charAt(lo) == s.charAt(hi) && isPalindrome(lo + 1, hi - 1);
        dp[lo][hi] = result;
        return result;
    }
}
