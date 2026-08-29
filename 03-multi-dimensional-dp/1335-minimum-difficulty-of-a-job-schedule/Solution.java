/**
 * LeetCode 1335. Minimum Difficulty of a Job Schedule
 * Approach: Top-down memoized recursion -- best(i, daysLeft) is the min
 * total difficulty scheduling jobDifficulty[i:] over daysLeft days. With 1
 * day left, everything remaining must be done that day (max of the
 * suffix); otherwise try every job range [i, j) as today's work.
 * Time: O(n^2 * d) | Space: O(n * d)
 */
class Solution {
    private int[] jobDifficulty;
    private int n;
    private Integer[][] dp;

    public int minDifficulty(int[] jobDifficulty, int d) {
        this.jobDifficulty = jobDifficulty;
        this.n = jobDifficulty.length;
        if (n < d) return -1;
        this.dp = new Integer[n][d + 1];
        return best(0, d);
    }

    private int best(int i, int daysLeft) {
        if (daysLeft == 1) return maxFrom(i, n);
        if (dp[i][daysLeft] != null) return dp[i][daysLeft];
        int result = Integer.MAX_VALUE;
        int todayMax = 0;
        // Leave at least (daysLeft - 1) jobs for the remaining days.
        for (int j = i; j <= n - daysLeft; j++) {
            todayMax = Math.max(todayMax, jobDifficulty[j]);
            result = Math.min(result, todayMax + best(j + 1, daysLeft - 1));
        }
        dp[i][daysLeft] = result;
        return result;
    }

    private int maxFrom(int i, int hi) {
        int max = 0;
        for (int j = i; j < hi; j++) max = Math.max(max, jobDifficulty[j]);
        return max;
    }
}
