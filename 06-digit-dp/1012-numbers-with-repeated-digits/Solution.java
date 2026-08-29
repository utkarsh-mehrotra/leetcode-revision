/**
 * LeetCode 1012. Numbers With Repeated Digits
 * Approach: Count the complement instead -- numbers in [1, n] with all
 * DISTINCT digits -- then subtract from n. Digit DP: solve(pos, usedMask,
 * started, tight) tries every digit not yet used (leading zeros don't
 * count as "used" until a nonzero digit starts the number). Once neither
 * bounded by n nor still in a leading-zero prefix, the remaining count
 * only depends on (pos, usedMask), which is the memoized state.
 * Time: O(len * 2^10) | Space: O(len * 2^10)
 */
class Solution {
    private int[] digits;
    private int len;
    private Integer[][] dp; // dp[pos][usedMask], valid only when not tight and started

    public int numDupDigitsAtMostN(int n) {
        String s = String.valueOf(n);
        len = s.length();
        digits = new int[len];
        for (int i = 0; i < len; i++) digits[i] = s.charAt(i) - '0';
        dp = new Integer[len][1 << 10];
        int uniqueCount = countUnique(0, 0, false, true);
        return n - uniqueCount;
    }

    private int countUnique(int pos, int usedMask, boolean started, boolean tight) {
        if (pos == len) return started ? 1 : 0;
        if (!tight && started && dp[pos][usedMask] != null) return dp[pos][usedMask];
        int limit = tight ? digits[pos] : 9;
        int total = 0;
        for (int d = 0; d <= limit; d++) {
            if (!started && d == 0) {
                total += countUnique(pos + 1, usedMask, false, tight && d == limit);
            } else if ((usedMask & (1 << d)) == 0) {
                total += countUnique(pos + 1, usedMask | (1 << d), true, tight && d == limit);
            }
        }
        if (!tight && started) dp[pos][usedMask] = total;
        return total;
    }
}
