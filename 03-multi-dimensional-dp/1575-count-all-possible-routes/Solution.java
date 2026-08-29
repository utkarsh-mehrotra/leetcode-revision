/**
 * LeetCode 1575. Count All Possible Routes
 * Approach: Top-down memoized recursion over (currentCity, fuelLeft) --
 * reaching `finish` always counts as one route (even mid-journey, since
 * the trip may continue through it), plus every other reachable city
 * consumes fuel equal to the distance and recurses.
 * Time: O(n^2 * fuel) | Space: O(n * fuel)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private int[] locations;
    private int finish;
    private Integer[][] dp;

    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        this.locations = locations;
        this.finish = finish;
        this.dp = new Integer[locations.length][fuel + 1];
        return solve(start, fuel);
    }

    private int solve(int curr, int fuelLeft) {
        if (dp[curr][fuelLeft] != null) return dp[curr][fuelLeft];
        long total = (curr == finish) ? 1 : 0;
        for (int next = 0; next < locations.length; next++) {
            if (next == curr) continue;
            int cost = Math.abs(locations[curr] - locations[next]);
            if (cost <= fuelLeft) {
                total = (total + solve(next, fuelLeft - cost)) % MOD;
            }
        }
        dp[curr][fuelLeft] = (int) total;
        return (int) total;
    }
}
