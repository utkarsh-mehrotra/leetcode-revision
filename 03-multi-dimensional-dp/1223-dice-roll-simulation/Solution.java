/**
 * LeetCode 1223. Dice Roll Simulation
 * Approach: Top-down memoized recursion over (rollsLeft, lastValue,
 * streak) -- try every next face; repeating lastValue is only legal while
 * streak hasn't hit rollMax for that face, and any other face resets the
 * streak to 1.
 * Time: O(n * 6 * maxRollMax) | Space: O(n * 6 * maxRollMax)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private int[] rollMax;
    private Integer[][][] dp;

    public int dieSimulator(int n, int[] rollMax) {
        this.rollMax = rollMax;
        this.dp = new Integer[n + 1][7][16];
        return solve(n, 0, 0); // lastValue 0 = "no roll yet"
    }

    private int solve(int rollsLeft, int lastValue, int streak) {
        if (rollsLeft == 0) return 1;
        if (dp[rollsLeft][lastValue][streak] != null) return dp[rollsLeft][lastValue][streak];
        long total = 0;
        for (int face = 1; face <= 6; face++) {
            if (face == lastValue) {
                if (streak < rollMax[face - 1]) {
                    total = (total + solve(rollsLeft - 1, face, streak + 1)) % MOD;
                }
            } else {
                total = (total + solve(rollsLeft - 1, face, 1)) % MOD;
            }
        }
        dp[rollsLeft][lastValue][streak] = (int) total;
        return (int) total;
    }
}
