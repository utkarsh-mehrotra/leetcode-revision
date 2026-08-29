/**
 * LeetCode 639. Decode Ways II
 * Approach: Top-down memoized recursion -- ways(i) is the number of ways
 * to decode s[i:], extending by a wildcard-aware single digit or two-digit
 * group.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private String s;
    private Long[] memo;

    public int numDecodings(String s) {
        this.s = s;
        this.memo = new Long[s.length() + 1];
        return (int) ways(0);
    }

    private long ways(int i) {
        int n = s.length();
        if (i == n) return 1;
        if (memo[i] != null) return memo[i];
        long result = (oneDigitWays(s.charAt(i)) * ways(i + 1)) % MOD;
        if (i + 1 < n) {
            result = (result + twoDigitWays(s.charAt(i), s.charAt(i + 1)) * ways(i + 2)) % MOD;
        }
        memo[i] = result;
        return result;
    }

    private long oneDigitWays(char c) {
        if (c == '*') return 9;
        return c == '0' ? 0 : 1;
    }

    private long twoDigitWays(char a, char b) {
        if (a == '*' && b == '*') return 15; // 11-19, 21-26
        if (a == '*') {
            return (b - '0' <= 6) ? 2 : 1; // "1b" always valid, "2b" valid if b <= 6
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
