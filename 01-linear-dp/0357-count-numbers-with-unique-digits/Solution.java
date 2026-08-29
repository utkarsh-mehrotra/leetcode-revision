/**
 * LeetCode 357. Count Numbers with Unique Digits
 * Approach: Top-down memoized recursion -- exactlyK(k) counts k-digit
 * numbers (k >= 1) with all-unique digits via exactlyK(k) = exactlyK(k-1) *
 * (9 - (k-2)) for k >= 2 (one fewer digit choice at each new position),
 * summed across all lengths 1..n plus the single number 0.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private Integer[] dp;

    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;
        dp = new Integer[n + 1];
        int total = 1; // the number 0
        for (int k = 1; k <= n; k++) {
            total += exactlyK(k);
        }
        return total;
    }

    private int exactlyK(int k) {
        if (k == 1) return 9;
        if (dp[k] != null) return dp[k];
        int availableDigits = 9 - (k - 2);
        int result = availableDigits > 0 ? exactlyK(k - 1) * availableDigits : 0;
        dp[k] = result;
        return result;
    }
}
