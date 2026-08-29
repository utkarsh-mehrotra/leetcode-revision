import java.util.Arrays;

/**
 * LeetCode 1547. Minimum Cost to Cut a Stick
 * Approach: Sort the cut positions and pad with the stick's own endpoints
 * (0 and n), so cuts[loIdx..hiIdx] with sentinels form an interval DP:
 * best(loIdx, hiIdx) is the min cost to make every cut strictly between
 * them, where cutting at k first costs the current stick length
 * (cuts[hiIdx] - cuts[loIdx]) plus the best cost of the two resulting pieces.
 * Time: O(m^3) | Space: O(m^2)
 */
class Solution {
    private int[] cuts;
    private Integer[][] dp;

    public int minCost(int n, int[] cuts) {
        int m = cuts.length;
        this.cuts = new int[m + 2];
        this.cuts[0] = 0;
        this.cuts[m + 1] = n;
        for (int i = 0; i < m; i++) this.cuts[i + 1] = cuts[i];
        Arrays.sort(this.cuts);
        this.dp = new Integer[m + 2][m + 2];
        return best(0, m + 1);
    }

    private int best(int loIdx, int hiIdx) {
        if (hiIdx - loIdx < 2) return 0; // no cut positions strictly between them
        if (dp[loIdx][hiIdx] != null) return dp[loIdx][hiIdx];
        int result = Integer.MAX_VALUE;
        int stickLength = cuts[hiIdx] - cuts[loIdx];
        for (int k = loIdx + 1; k < hiIdx; k++) {
            result = Math.min(result, stickLength + best(loIdx, k) + best(k, hiIdx));
        }
        dp[loIdx][hiIdx] = result;
        return result;
    }
}
