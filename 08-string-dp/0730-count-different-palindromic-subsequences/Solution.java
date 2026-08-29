/**
 * LeetCode 730. Count Different Palindromic Subsequences
 * Approach: Interval DP over (lo, hi) counting distinct non-empty
 * palindromic subsequences of s[lo..hi]. If the endpoints differ,
 * inclusion-exclusion over the two smaller ranges avoids double-counting
 * their overlap. If they match, every palindrome of the inner range
 * wrapped in that letter is new, plus the letter alone and (if no/one
 * more occurrence of it sits strictly inside) an extra "letter doubled"
 * case -- handled by locating the innermost matching pair of that letter.
 * Time: O(n^2) | Space: O(n^2)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private String s;
    private Long[][] dp;

    public int countPalindromicSubsequences(String s) {
        this.s = s;
        int n = s.length();
        this.dp = new Long[n][n];
        return (int) count(0, n - 1);
    }

    private long count(int lo, int hi) {
        if (lo > hi) return 0;
        if (lo == hi) return 1;
        if (dp[lo][hi] != null) return dp[lo][hi];

        long result;
        char a = s.charAt(lo), b = s.charAt(hi);
        if (a != b) {
            result = (count(lo + 1, hi) + count(lo, hi - 1) - count(lo + 1, hi - 1) + MOD) % MOD;
        } else {
            int left = lo + 1, right = hi - 1;
            while (left <= right && s.charAt(left) != a) left++;
            while (left <= right && s.charAt(right) != a) right--;
            if (left > right) {
                // No other occurrence of `a` strictly inside: adding it on both
                // sides doubles the inner count, plus "a" alone and "aa".
                result = (2 * count(lo + 1, hi - 1) % MOD + 2) % MOD;
            } else if (left == right) {
                // Exactly one more occurrence of `a` inside: "aa" isn't new
                // beyond wrapping, only "a" alone is added.
                result = (2 * count(lo + 1, hi - 1) % MOD + 1) % MOD;
            } else {
                // Two or more further occurrences: wrapping the strictly-inner
                // range between them was already counted once too many times.
                result = (2 * count(lo + 1, hi - 1) % MOD - count(left + 1, right - 1) % MOD + MOD) % MOD;
            }
        }
        dp[lo][hi] = result;
        return result;
    }
}
