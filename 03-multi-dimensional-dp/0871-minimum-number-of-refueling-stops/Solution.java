/**
 * LeetCode 871. Minimum Number of Refueling Stops
 * Approach: Top-down memoized recursion -- reach(i, stops) is the
 * farthest distance achievable using exactly `stops` refuels chosen from
 * the first i stations. A station can only be used if it's within the
 * distance already reachable with one fewer stop. The answer is the
 * smallest stop count whose reach covers target.
 * Time: O(n^2) | Space: O(n^2)
 */
class Solution {
    private int[][] stations;
    private int n;
    private int startFuel;
    private Long[][] dp;
    private static final long UNREACHABLE = -1;

    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        this.stations = stations;
        this.n = stations.length;
        this.startFuel = startFuel;
        this.dp = new Long[n + 1][n + 1];
        for (int stops = 0; stops <= n; stops++) {
            if (reach(n, stops) >= target) return stops;
        }
        return -1;
    }

    private long reach(int i, int stops) {
        if (stops == 0) return startFuel;
        if (stops > i) return UNREACHABLE;
        if (dp[i][stops] != null) return dp[i][stops];
        long skip = reach(i - 1, stops);
        long result = skip;
        long prevReach = reach(i - 1, stops - 1);
        if (prevReach != UNREACHABLE && prevReach >= stations[i - 1][0]) {
            result = Math.max(result, prevReach + stations[i - 1][1]);
        }
        dp[i][stops] = result;
        return result;
    }
}
