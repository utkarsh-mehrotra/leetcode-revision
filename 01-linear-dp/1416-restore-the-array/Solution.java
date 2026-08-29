/**
 * LeetCode 1416. Restore The Array
 * Approach: Suffix DP -- dp[i] = number of ways to split s[i:] into valid
 * numbers each in [1, k]. From position i, extend the next number digit by
 * digit only while its value stays <= k; a leading zero at i makes that
 * position unusable entirely.
 * Time: O(n * digits(k)) | Space: O(n)
 */
class Solution {
    private static final int MOD = 1_000_000_007;

    public int numberOfArrays(String s, int k) {
        int n = s.length();
        long[] dp = new long[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                dp[i] = 0;
                continue;
            }
            long value = 0;
            for (int j = i; j < n; j++) {
                value = value * 10 + (s.charAt(j) - '0');
                if (value > k) break;
                dp[i] = (dp[i] + dp[j + 1]) % MOD;
            }
        }
        return (int) dp[0];
    }
}
