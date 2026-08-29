/**
 * LeetCode 1659. Maximize Grid Happiness
 * Approach: Process cells in row-major order. Top-down memoized recursion
 * over (pos, introvertsLeft, extrovertsLeft, profile) -- profile is a
 * base-3 number encoding the types (empty/introvert/extrovert) of the
 * last n placed cells, which is exactly enough to know the "up" neighbor
 * (about to scroll out of the window) and the "left" neighbor (the
 * newest digit) when placing the next cell. Each placement adds its own
 * base happiness plus the mutual reaction with every already-placed
 * occupied neighbor.
 * Time: O(m*n * introverts * extroverts * 3^n) | Space: same
 */
class Solution {
    private int m, n;
    private int pow3nMinus1;
    private Integer[][][][] dp;

    public int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
        this.m = m;
        this.n = n;
        this.pow3nMinus1 = pow(3, n - 1);
        int profileSpace = pow3nMinus1 * 3;
        dp = new Integer[m * n + 1][introvertsCount + 1][extrovertsCount + 1][profileSpace];
        return solve(0, introvertsCount, extrovertsCount, 0);
    }

    private int pow(int base, int exp) {
        int result = 1;
        for (int i = 0; i < exp; i++) result *= base;
        return result;
    }

    private int reaction(int type) { // type: 0=empty, 1=introvert, 2=extrovert
        return type == 1 ? -30 : (type == 2 ? 20 : 0);
    }

    private int solve(int pos, int introvertsLeft, int extrovertsLeft, int profile) {
        if (pos == m * n) return 0;
        if (dp[pos][introvertsLeft][extrovertsLeft][profile] != null) {
            return dp[pos][introvertsLeft][extrovertsLeft][profile];
        }

        int topType = profile / pow3nMinus1;          // cell directly above pos
        int leftType = (pos % n == 0) ? 0 : profile % 3; // cell directly left of pos, only within the same row
        int shiftedProfile = profile % pow3nMinus1;

        int best = solve(pos + 1, introvertsLeft, extrovertsLeft, shiftedProfile * 3); // leave empty

        if (introvertsLeft > 0) {
            int gain = 120;
            if (leftType != 0) gain += reaction(1) + reaction(leftType);
            if (topType != 0) gain += reaction(1) + reaction(topType);
            int nextProfile = shiftedProfile * 3 + 1;
            best = Math.max(best, gain + solve(pos + 1, introvertsLeft - 1, extrovertsLeft, nextProfile));
        }
        if (extrovertsLeft > 0) {
            int gain = 40;
            if (leftType != 0) gain += reaction(2) + reaction(leftType);
            if (topType != 0) gain += reaction(2) + reaction(topType);
            int nextProfile = shiftedProfile * 3 + 2;
            best = Math.max(best, gain + solve(pos + 1, introvertsLeft, extrovertsLeft - 1, nextProfile));
        }

        dp[pos][introvertsLeft][extrovertsLeft][profile] = best;
        return best;
    }
}
