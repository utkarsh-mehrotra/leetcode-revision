/**
 * LeetCode 902. Numbers At Most N Given Digit Set
 * Approach: Every number shorter than N's digit length is automatically
 * valid (digits.length^L choices for each length L). For numbers with the
 * same length as N, digit DP: solve(pos, tight) tries every allowed digit
 * at this position (bounded by N's digit when tight), and once we're no
 * longer bounded by N (tight=false) the remaining count only depends on
 * how many positions are left, so that's the memoized state.
 * Time: O(len * digits.length) | Space: O(len)
 */
class Solution {
    private String[] digits;
    private String n;
    private Integer[] dp; // dp[pos], valid only when not tight

    public int atMostNGivenDigitSet(String[] digits, int n) {
        this.digits = digits;
        this.n = String.valueOf(n);
        int len = this.n.length();
        this.dp = new Integer[len + 1];

        long shorterLengths = 0;
        long power = 1;
        for (int l = 1; l < len; l++) {
            power *= digits.length;
            shorterLengths += power;
        }
        return (int) (shorterLengths + solve(0, true));
    }

    private int solve(int pos, boolean tight) {
        if (pos == n.length()) return 1;
        if (!tight && dp[pos] != null) return dp[pos];
        int limit = tight ? n.charAt(pos) - '0' : 9;
        int total = 0;
        for (String d : digits) {
            int digit = d.charAt(0) - '0';
            if (digit > limit) continue;
            total += solve(pos + 1, tight && digit == limit);
        }
        if (!tight) dp[pos] = total;
        return total;
    }
}
