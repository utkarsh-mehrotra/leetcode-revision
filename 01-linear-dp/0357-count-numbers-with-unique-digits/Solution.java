/**
 * LeetCode 357. Count Numbers with Unique Digits
 * Approach: Closed-form combinatorics -- for k-digit numbers (k >= 2) with
 * all-unique digits, the leading digit has 9 choices (1-9) and each
 * subsequent digit has one fewer available choice than the last;
 * accumulate across digit lengths up to n.
 * Time: O(n) | Space: O(1)
 */
class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;
        int total = 10; // all 1-digit numbers, including 0
        int uniqueDigitCount = 9;
        int availableDigits = 9;
        for (int k = 2; k <= n && availableDigits > 0; k++) {
            uniqueDigitCount *= availableDigits;
            total += uniqueDigitCount;
            availableDigits--;
        }
        return total;
    }
}
