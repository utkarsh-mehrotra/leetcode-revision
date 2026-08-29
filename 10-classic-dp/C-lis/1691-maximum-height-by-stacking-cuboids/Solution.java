import java.util.Arrays;

/**
 * LeetCode 1691. Maximum Height by Stacking Cuboids
 * Approach: Sort each cuboid's own 3 dimensions ascending (the optimal
 * orientation for stacking always has width <= length <= height), then
 * sort the cuboids themselves lexicographically. This makes "cuboid j can
 * sit beneath cuboid i" exactly a componentwise-<= check, and the problem
 * becomes LIS-style: top-down memoized recursion -- heightEndingAt(i) is
 * the tallest stack with cuboid i on top, built from every valid j below it.
 * Time: O(n^2) | Space: O(n)
 */
class Solution {
    private int[][] cuboids;
    private Integer[] dp;

    public int maxHeight(int[][] cuboids) {
        for (int[] c : cuboids) Arrays.sort(c);
        Arrays.sort(cuboids, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            if (a[1] != b[1]) return a[1] - b[1];
            return a[2] - b[2];
        });
        this.cuboids = cuboids;
        this.dp = new Integer[cuboids.length];
        int best = 0;
        for (int i = 0; i < cuboids.length; i++) {
            best = Math.max(best, heightEndingAt(i));
        }
        return best;
    }

    private int heightEndingAt(int i) {
        if (dp[i] != null) return dp[i];
        int best = cuboids[i][2];
        for (int j = 0; j < i; j++) {
            if (cuboids[j][0] <= cuboids[i][0] && cuboids[j][1] <= cuboids[i][1] && cuboids[j][2] <= cuboids[i][2]) {
                best = Math.max(best, heightEndingAt(j) + cuboids[i][2]);
            }
        }
        dp[i] = best;
        return best;
    }
}
