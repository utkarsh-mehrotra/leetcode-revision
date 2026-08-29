/**
 * LeetCode 818. Race Car
 * Approach: Top-down memoized recursion -- find the smallest number of
 * forward-only "A" moves whose distance 2^steps - 1 reaches or passes
 * target. If it lands exactly, that's optimal. Otherwise either overshoot
 * fully then reverse and solve the (smaller) remaining gap, or stop one
 * move short, reverse, drive back j steps for every possible j, reverse
 * again, and solve the new remaining gap -- taking the best over all j.
 * Time: O(target log(target)) | Space: O(target)
 */
class Solution {
    private Integer[] dp;

    public int racecar(int target) {
        dp = new Integer[target + 1];
        return solve(target);
    }

    private int solve(int t) {
        if (t == 0) return 0;
        if (dp[t] != null) return dp[t];

        int steps = 0;
        long dist = 0;
        while (dist < t) {
            steps++;
            dist = (1L << steps) - 1;
        }

        int best;
        if (dist == t) {
            best = steps;
        } else {
            best = steps + 1 + solve((int) (dist - t));
            long dist2 = (1L << (steps - 1)) - 1;
            for (int j = 0; j < steps - 1; j++) {
                long extra = (1L << j) - 1;
                int candidate = (steps - 1) + 1 + j + 1 + solve((int) (t - dist2 + extra));
                best = Math.min(best, candidate);
            }
        }
        dp[t] = best;
        return best;
    }
}
