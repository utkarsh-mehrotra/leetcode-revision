/**
 * LeetCode 256. Paint House
 * Approach: Top-down memoized recursion over (house, previousColor) --
 * minCost(i, prev) picks the cheapest color at house i (excluding prev)
 * plus the best cost for the remaining houses.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private int[][] costs;
    private Integer[][] memo;

    public int minCost(int[][] costs) {
        if (costs.length == 0) return 0;
        this.costs = costs;
        this.memo = new Integer[costs.length][4]; // prevColor offset by 1 (-1..2)
        return solve(0, -1);
    }

    private int solve(int i, int prevColor) {
        if (i == costs.length) return 0;
        if (memo[i][prevColor + 1] != null) return memo[i][prevColor + 1];
        int best = Integer.MAX_VALUE;
        for (int c = 0; c < 3; c++) {
            if (c != prevColor) {
                best = Math.min(best, costs[i][c] + solve(i + 1, c));
            }
        }
        memo[i][prevColor + 1] = best;
        return best;
    }
}
