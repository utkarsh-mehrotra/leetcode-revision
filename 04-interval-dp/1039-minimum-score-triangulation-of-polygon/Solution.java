/**
 * LeetCode 1039. Minimum Score Triangulation of Polygon
 * Approach: Interval DP -- best(lo, hi) is the min triangulation score for
 * the polygon fan spanned by vertices lo..hi, trying every third vertex k
 * that closes a triangle (lo, k, hi) with the two remaining sub-polygons.
 * Time: O(n^3) | Space: O(n^2)
 */
class Solution {
    private int[] values;
    private Integer[][] dp;

    public int minScoreTriangulation(int[] values) {
        this.values = values;
        this.dp = new Integer[values.length][values.length];
        return best(0, values.length - 1);
    }

    private int best(int lo, int hi) {
        if (hi - lo < 2) return 0; // fewer than 3 vertices: no triangle to form
        if (dp[lo][hi] != null) return dp[lo][hi];
        int result = Integer.MAX_VALUE;
        for (int k = lo + 1; k < hi; k++) {
            int triangle = values[lo] * values[k] * values[hi];
            result = Math.min(result, best(lo, k) + best(k, hi) + triangle);
        }
        dp[lo][hi] = result;
        return result;
    }
}
