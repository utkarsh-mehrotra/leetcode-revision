/**
 * LeetCode 2742. Painting the Walls
 * Approach: Assigning wall i to the paid painter costs cost[i] and yields
 * time[i] + 1 units of "coverage" (the wall itself, plus time[i] days
 * during which the free painter can cover another wall). We need total
 * coverage >= n. Top-down memoized recursion over (index, coverageNeeded)
 * -- skip or pay for wall i, capping coverageNeeded at 0.
 * Time: O(n^2) | Space: O(n^2)
 */
class Solution {
    private int[] cost, time;
    private int n;
    private Integer[][] dp;
    private static final int INF = Integer.MAX_VALUE / 2;

    public int paintWalls(int[] cost, int[] time) {
        this.cost = cost;
        this.time = time;
        this.n = cost.length;
        this.dp = new Integer[n][n + 1];
        return solve(0, n);
    }

    private int solve(int i, int coverageNeeded) {
        if (coverageNeeded <= 0) return 0;
        if (i == n) return INF;
        if (dp[i][coverageNeeded] != null) return dp[i][coverageNeeded];
        int skip = solve(i + 1, coverageNeeded);
        int nextNeeded = Math.max(0, coverageNeeded - (time[i] + 1));
        int take = cost[i] + solve(i + 1, nextNeeded);
        int result = Math.min(skip, take);
        dp[i][coverageNeeded] = result;
        return result;
    }
}
