/**
 * LeetCode 600. Non-negative Integers without Consecutive Ones
 * Approach: Digit DP over n's binary representation -- solve(pos, prevBit,
 * tight) counts completions of the remaining bits that never place a 1
 * right after another 1. `tight` tracks whether the prefix built so far
 * still equals n's prefix (capping the next bit); once we go below n
 * (tight=false), the remaining count only depends on (pos, prevBit), so
 * that's the memoized state.
 * Time: O(bits) | Space: O(bits)
 */
class Solution {
    private String bits;
    private Integer[][] dp; // dp[pos][prevBit], valid only when not tight

    public int findIntegers(int n) {
        bits = Integer.toBinaryString(n);
        dp = new Integer[bits.length() + 1][2];
        return solve(0, 0, true);
    }

    private int solve(int pos, int prevBit, boolean tight) {
        if (pos == bits.length()) return 1;
        if (!tight && dp[pos][prevBit] != null) return dp[pos][prevBit];
        int limit = tight ? bits.charAt(pos) - '0' : 1;
        int total = 0;
        for (int b = 0; b <= limit; b++) {
            if (prevBit == 1 && b == 1) continue; // would create consecutive ones
            total += solve(pos + 1, b, tight && b == limit);
        }
        if (!tight) dp[pos][prevBit] = total;
        return total;
    }
}
