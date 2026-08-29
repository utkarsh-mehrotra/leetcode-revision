/**
 * LeetCode 1269. Number of Ways to Stay in the Same Place After Some Steps
 * Approach: Top-down memoized recursion over (stepsLeft, pos) -- ways to
 * end back at 0 using exactly stepsLeft more moves from pos, each move
 * either staying, or moving left/right within [0, arrLen). Position can
 * never usefully exceed stepsLeft (no way back in time), so it's also
 * capped there to bound the state space.
 * Time: O(steps * min(steps, arrLen)) | Space: O(steps * min(steps, arrLen))
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private int arrLen;
    private Integer[][] dp;

    public int numWays(int steps, int arrLen) {
        this.arrLen = arrLen;
        int maxPos = Math.min(arrLen - 1, steps);
        this.dp = new Integer[steps + 1][maxPos + 1];
        return solve(steps, 0);
    }

    private int solve(int stepsLeft, int pos) {
        if (pos < 0 || pos >= arrLen) return 0;
        if (pos > stepsLeft) return 0; // can never return to 0 in time
        if (stepsLeft == 0) return pos == 0 ? 1 : 0;
        if (dp[stepsLeft][pos] != null) return dp[stepsLeft][pos];
        long total = solve(stepsLeft - 1, pos);
        total = (total + solve(stepsLeft - 1, pos - 1)) % MOD;
        total = (total + solve(stepsLeft - 1, pos + 1)) % MOD;
        dp[stepsLeft][pos] = (int) total;
        return (int) total;
    }
}
