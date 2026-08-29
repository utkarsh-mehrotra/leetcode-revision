/**
 * LeetCode 1641. Count Sorted Vowel Strings
 * Approach: Top-down memoized recursion -- ways(length, startVowel) is
 * the count of non-decreasing (by vowel order) strings of this length
 * whose first character is at least `startVowel`, summed over every
 * choice of first character from startVowel through 'u'.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private Integer[][] dp;

    public int countVowelStrings(int n) {
        dp = new Integer[n + 1][5];
        return ways(n, 0);
    }

    private int ways(int length, int startVowel) {
        if (length == 0) return 1;
        if (dp[length][startVowel] != null) return dp[length][startVowel];
        int total = 0;
        for (int v = startVowel; v < 5; v++) {
            total += ways(length - 1, v);
        }
        dp[length][startVowel] = total;
        return total;
    }
}
