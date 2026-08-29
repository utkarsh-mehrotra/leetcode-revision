/**
 * LeetCode 32. Longest Valid Parentheses
 * Approach: Top-down memoized recursion -- validEndingAt(i) is the length
 * of the longest valid parentheses substring ending exactly at i. A ')'
 * either closes a matching '(' right before it (extending whatever valid
 * run preceded that), or extends a valid run that itself ends in ')' by
 * matching the '(' just before that run began.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private String s;
    private Integer[] dp;

    public int longestValidParentheses(String s) {
        this.s = s;
        this.dp = new Integer[s.length()];
        int best = 0;
        for (int i = 0; i < s.length(); i++) {
            best = Math.max(best, validEndingAt(i));
        }
        return best;
    }

    private int validEndingAt(int i) {
        if (i < 0) return 0;
        if (s.charAt(i) == '(') return 0;
        if (dp[i] != null) return dp[i];
        int result = 0;
        if (i >= 1 && s.charAt(i - 1) == '(') {
            result = 2 + validEndingAt(i - 2);
        } else {
            int prevRun = validEndingAt(i - 1);
            int matchIndex = i - prevRun - 1;
            if (prevRun > 0 && matchIndex >= 0 && s.charAt(matchIndex) == '(') {
                result = prevRun + 2 + validEndingAt(matchIndex - 1);
            }
        }
        dp[i] = result;
        return result;
    }
}
