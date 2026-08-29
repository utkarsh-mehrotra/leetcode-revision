/**
 * LeetCode 1416. Restore The Array
 * Approach: Top-down memoized recursion -- ways(i) is the number of ways
 * to split s[i:] into valid numbers each in [1, k], extending the current
 * number one digit at a time while it stays within k.
 * Time: O(n * digits(k)) | Space: O(n)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private String s;
    private long k;
    private Long[] dp;

    public int numberOfArrays(String s, int k) {
        this.s = s;
        this.k = k;
        this.dp = new Long[s.length() + 1];
        return (int) ways(0);
    }

    private long ways(int i) {
        int n = s.length();
        if (i == n) return 1;
        if (s.charAt(i) == '0') return 0;
        if (dp[i] != null) return dp[i];
        long result = 0;
        long value = 0;
        for (int j = i; j < n; j++) {
            value = value * 10 + (s.charAt(j) - '0');
            if (value > k) break;
            result = (result + ways(j + 1)) % MOD;
        }
        dp[i] = result;
        return result;
    }
}
