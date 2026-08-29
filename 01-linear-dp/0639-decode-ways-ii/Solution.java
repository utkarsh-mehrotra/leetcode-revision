/**
 * LeetCode 639. Decode Ways II
 * Approach: Rolling DP identical in spirit to Decode Ways, but each
 * transition must count the multiple digit values '*' can represent --
 * 9 options standalone, and a fixed count for each two-character pattern
 * depending on which side (if either) is a wildcard.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    private static final int MOD = 1_000_000_007;

    public int numDecodings(String s) {
        long prev2 = 1; // dp[i-2], empty prefix
        long prev1 = oneDigitWays(s.charAt(0)); // dp[1]
        if (s.length() == 1) return (int) prev1;

        for (int i = 2; i <= s.length(); i++) {
            char a = s.charAt(i - 2);
            char b = s.charAt(i - 1);
            long curr = (oneDigitWays(b) * prev1) % MOD;
            curr = (curr + twoDigitWays(a, b) * prev2) % MOD;
            prev2 = prev1;
            prev1 = curr;
        }
        return (int) prev1;
    }

    private long oneDigitWays(char c) {
        if (c == '*') return 9;
        return c == '0' ? 0 : 1;
    }

    private long twoDigitWays(char a, char b) {
        if (a == '*' && b == '*') return 15; // 11-19, 21-26
        if (a == '*') {
            // "1b" is always valid (10-19); "2b" is valid only if b <= 6 (20-26).
            return (b - '0' <= 6) ? 2 : 1;
        }
        if (b == '*') {
            if (a == '1') return 9; // 11-19
            if (a == '2') return 6; // 21-26
            return 0;
        }
        int value = (a - '0') * 10 + (b - '0');
        return (value >= 10 && value <= 26) ? 1 : 0;
    }
}
