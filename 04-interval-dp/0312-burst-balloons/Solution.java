/**
 * LeetCode 312. Burst Balloons
 * Approach: Interval DP -- pad the array with a 1 at each end so every
 * real balloon has boundary neighbors. best(lo, hi) is the max coins from
 * bursting every balloon strictly between lo and hi, thinking in reverse:
 * choosing which balloon k is burst LAST in that open interval, so lo and
 * hi are still its immediate neighbors at that moment.
 * Time: O(n^3) | Space: O(n^2)
 */
class Solution {
    private int[] balloons;
    private Integer[][] dp;

    public int maxCoins(int[] nums) {
        int n = nums.length;
        balloons = new int[n + 2];
        balloons[0] = 1;
        balloons[n + 1] = 1;
        for (int i = 0; i < n; i++) balloons[i + 1] = nums[i];
        dp = new Integer[n + 2][n + 2];
        return best(0, n + 1);
    }

    private int best(int lo, int hi) {
        if (hi - lo < 2) return 0; // no balloons strictly between lo and hi
        if (dp[lo][hi] != null) return dp[lo][hi];
        int result = 0;
        for (int k = lo + 1; k < hi; k++) {
            int coins = balloons[lo] * balloons[k] * balloons[hi];
            result = Math.max(result, best(lo, k) + best(k, hi) + coins);
        }
        dp[lo][hi] = result;
        return result;
    }
}
