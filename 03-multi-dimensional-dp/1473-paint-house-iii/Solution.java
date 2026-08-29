/**
 * LeetCode 1473. Paint House III
 * Approach: Top-down memoized recursion over (house, prevColor,
 * neighborhoodsSoFar) -- an already-painted house has only one legal
 * color; otherwise try every color, incrementing the neighborhood count
 * only when it differs from the previous house's color.
 * Time: O(m * n^2 * target) | Space: O(m * n * target)
 */
class Solution {
    private int[] houses;
    private int[][] cost;
    private int m, n, target;
    private Integer[][][] dp;
    private static final int INF = Integer.MAX_VALUE / 2;

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        this.houses = houses;
        this.cost = cost;
        this.m = m;
        this.n = n;
        this.target = target;
        this.dp = new Integer[m][n + 1][target + 1];
        int result = solve(0, 0, 0);
        return result >= INF ? -1 : result;
    }

    private int solve(int house, int prevColor, int neighborhoods) {
        if (neighborhoods > target) return INF;
        if (house == m) return neighborhoods == target ? 0 : INF;
        if (dp[house][prevColor][neighborhoods] != null) return dp[house][prevColor][neighborhoods];
        int best = INF;
        if (houses[house] != 0) {
            int color = houses[house];
            int nextNeighborhoods = neighborhoods + (color == prevColor ? 0 : 1);
            best = solve(house + 1, color, nextNeighborhoods);
        } else {
            for (int color = 1; color <= n; color++) {
                int nextNeighborhoods = neighborhoods + (color == prevColor ? 0 : 1);
                int candidate = cost[house][color - 1] + solve(house + 1, color, nextNeighborhoods);
                best = Math.min(best, candidate);
            }
        }
        dp[house][prevColor][neighborhoods] = best;
        return best;
    }
}
