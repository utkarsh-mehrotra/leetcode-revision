import java.util.Arrays;

/**
 * LeetCode 646. Maximum Length of Pair Chain
 * Approach: Top-down memoized recursion (LIS-style) -- after sorting pairs
 * by start, chain(i) is the longest chain ending at pair i, built from the
 * best chain(j) over every earlier pair j whose end precedes pair i's start.
 * Time: O(n^2) | Space: O(n)
 */
class Solution {
    private int[][] pairs;
    private Integer[] dp;

    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));
        this.pairs = pairs;
        this.dp = new Integer[pairs.length];
        int best = 0;
        for (int i = 0; i < pairs.length; i++) {
            best = Math.max(best, chain(i));
        }
        return best;
    }

    private int chain(int i) {
        if (dp[i] != null) return dp[i];
        int best = 1;
        for (int j = 0; j < i; j++) {
            if (pairs[j][1] < pairs[i][0]) {
                best = Math.max(best, chain(j) + 1);
            }
        }
        dp[i] = best;
        return best;
    }
}
