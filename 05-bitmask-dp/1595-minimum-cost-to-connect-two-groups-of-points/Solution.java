/**
 * LeetCode 1595. Minimum Cost to Connect Two Groups of Points
 * Approach: Top-down memoized recursion over (i, coveredMask) -- process
 * group 1's points one at a time, each connecting to at least one group 2
 * point (try every j and recurse), tracking which group 2 points have
 * been touched so far. Once group 1 is exhausted, any still-uncovered
 * group 2 point must connect to whichever group 1 point is cheapest for
 * it (precomputed).
 * Time: O(size1 * 2^size2 * size2) | Space: O(size1 * 2^size2)
 */
class Solution {
    private int[][] cost;
    private int size1, size2;
    private int[] cheapestForGroup2; // cheapestForGroup2[j] = min cost[i][j] over all i
    private Integer[][] dp;

    public int connectTwoGroups(java.util.List<java.util.List<Integer>> cost) {
        size1 = cost.size();
        size2 = cost.get(0).size();
        this.cost = new int[size1][size2];
        cheapestForGroup2 = new int[size2];
        java.util.Arrays.fill(cheapestForGroup2, Integer.MAX_VALUE);
        for (int i = 0; i < size1; i++) {
            for (int j = 0; j < size2; j++) {
                this.cost[i][j] = cost.get(i).get(j);
                cheapestForGroup2[j] = Math.min(cheapestForGroup2[j], this.cost[i][j]);
            }
        }
        dp = new Integer[size1 + 1][1 << size2];
        return solve(0, 0);
    }

    private int solve(int i, int coveredMask) {
        int fullMask = (1 << size2) - 1;
        if (i == size1) {
            int extra = 0;
            for (int j = 0; j < size2; j++) {
                if ((coveredMask & (1 << j)) == 0) extra += cheapestForGroup2[j];
            }
            return extra;
        }
        if (dp[i][coveredMask] != null) return dp[i][coveredMask];
        int best = Integer.MAX_VALUE;
        for (int j = 0; j < size2; j++) {
            int candidate = cost[i][j] + solve(i + 1, coveredMask | (1 << j));
            best = Math.min(best, candidate);
        }
        dp[i][coveredMask] = best;
        return best;
    }
}
