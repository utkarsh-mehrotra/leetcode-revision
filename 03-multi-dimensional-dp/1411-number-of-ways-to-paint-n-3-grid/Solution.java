import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 1411. Number of Ways to Paint N x 3 Grid
 * Approach: Enumerate the 12 valid row patterns (3 cells, 3 colors,
 * adjacent cells differ) up front. Top-down memoized recursion over
 * (rowsLeft, prevPatternIndex) sums, over every pattern compatible with
 * the previous row (differs in all 3 columns), the ways to paint the rest.
 * Time: O(n * 12^2) | Space: O(n * 12)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private List<int[]> patterns;
    private List<List<Integer>> compatible; // compatible.get(i) = indices of patterns usable after patterns.get(i)
    private Long[][] dp;

    public int numOfWays(int n) {
        buildPatterns();
        dp = new Long[n + 1][patterns.size()];
        long total = 0;
        for (int p = 0; p < patterns.size(); p++) {
            total = (total + solve(n - 1, p)) % MOD;
        }
        return (int) total;
    }

    private void buildPatterns() {
        patterns = new ArrayList<>();
        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                if (b == a) continue;
                for (int c = 0; c < 3; c++) {
                    if (c == b) continue;
                    patterns.add(new int[]{a, b, c});
                }
            }
        }
        compatible = new ArrayList<>();
        for (int[] p : patterns) {
            List<Integer> list = new ArrayList<>();
            for (int q = 0; q < patterns.size(); q++) {
                int[] next = patterns.get(q);
                if (p[0] != next[0] && p[1] != next[1] && p[2] != next[2]) {
                    list.add(q);
                }
            }
            compatible.add(list);
        }
    }

    // Ways to paint `rowsLeft` more rows, given the current row used pattern `patternIdx`.
    private long solve(int rowsLeft, int patternIdx) {
        if (rowsLeft == 0) return 1;
        if (dp[rowsLeft][patternIdx] != null) return dp[rowsLeft][patternIdx];
        long total = 0;
        for (int next : compatible.get(patternIdx)) {
            total = (total + solve(rowsLeft - 1, next)) % MOD;
        }
        dp[rowsLeft][patternIdx] = total;
        return total;
    }
}
