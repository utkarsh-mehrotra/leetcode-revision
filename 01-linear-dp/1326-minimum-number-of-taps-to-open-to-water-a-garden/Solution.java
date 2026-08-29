/**
 * LeetCode 1326. Minimum Number of Taps to Open to Water a Garden
 * Approach: Top-down memoized recursion -- best(covered) is the fewest
 * taps needed to extend watered coverage from [0, covered) to the full
 * garden, jumping to the farthest reach achievable from any position
 * within the currently covered range (precomputed as a prefix max).
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private int[] maxReachUpTo;
    private Integer[] dp;
    private int n;
    private static final int INF = Integer.MAX_VALUE / 2;

    public int minTaps(int[] ranges) {
        n = ranges.length - 1;
        int[] farthestFrom = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int left = Math.max(0, i - ranges[i]);
            int right = Math.min(n, i + ranges[i]);
            farthestFrom[left] = Math.max(farthestFrom[left], right);
        }
        maxReachUpTo = new int[n + 1];
        int runningMax = 0;
        for (int i = 0; i <= n; i++) {
            runningMax = Math.max(runningMax, farthestFrom[i]);
            maxReachUpTo[i] = runningMax;
        }
        dp = new Integer[n + 1];
        int result = best(0);
        return result >= INF ? -1 : result;
    }

    private int best(int covered) {
        if (covered >= n) return 0;
        if (dp[covered] != null) return dp[covered];
        int reach = maxReachUpTo[covered];
        int result = (reach > covered) ? 1 + best(reach) : INF;
        dp[covered] = result;
        return result;
    }
}
