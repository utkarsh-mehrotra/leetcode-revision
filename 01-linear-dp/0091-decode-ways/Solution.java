/**
 * LeetCode 91. Decode Ways
 * Approach: Top-down memoized recursion -- ways(i) is the number of ways
 * to decode the suffix s[i:], extending by a valid single digit or valid
 * two-digit group.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private String s;
    private Integer[] memo;

    public int numDecodings(String s) {
        this.s = s;
        this.memo = new Integer[s.length() + 1];
        return ways(0);
    }

    private int ways(int i) {
        int n = s.length();
        if (i == n) return 1;
        if (s.charAt(i) == '0') return 0;
        if (memo[i] != null) return memo[i];
        int result = ways(i + 1);
        if (i + 1 < n) {
            int twoDigit = (s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0');
            if (twoDigit <= 26) result += ways(i + 2);
        }
        memo[i] = result;
        return result;
    }
}
