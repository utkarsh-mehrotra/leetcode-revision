/**
 * LeetCode 1024. Video Stitching
 * Approach: Same shape as Minimum Taps to Water a Garden -- precompute,
 * for every start second, the farthest end reachable by some clip, take a
 * prefix max, then top-down memoized recursion best(covered) greedily
 * jumps to the farthest reach achievable from within [0, covered].
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private int[] maxReachUpTo;
    private Integer[] dp;
    private int t;
    private static final int INF = Integer.MAX_VALUE / 2;

    public int videoStitching(int[][] clips, int time) {
        this.t = time;
        int[] farthestFrom = new int[time + 1];
        for (int[] clip : clips) {
            if (clip[0] <= time) {
                farthestFrom[clip[0]] = Math.max(farthestFrom[clip[0]], clip[1]);
            }
        }
        maxReachUpTo = new int[time + 1];
        int runningMax = 0;
        for (int i = 0; i <= time; i++) {
            runningMax = Math.max(runningMax, farthestFrom[i]);
            maxReachUpTo[i] = runningMax;
        }
        dp = new Integer[time + 1];
        int result = best(0);
        return result >= INF ? -1 : result;
    }

    private int best(int covered) {
        if (covered >= t) return 0;
        if (dp[covered] != null) return dp[covered];
        int reach = maxReachUpTo[covered];
        int result = (reach > covered) ? 1 + best(reach) : INF;
        dp[covered] = result;
        return result;
    }
}
