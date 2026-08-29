import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * LeetCode 2977. Minimum Cost to Convert String II
 * Approach: Map every distinct substring appearing in original/changed to
 * a node id, then Floyd-Warshall gives the min cost to turn any such
 * substring into any other via chained single-step substitutions. A
 * position-indexed DP over source (dp[i] = min cost to convert
 * source[0:i] into target[0:i]) then tries, at each position, either
 * matching characters for free or consuming a substitution of one of the
 * few known lengths.
 * Time: O(m^3 + n * distinctLengths) | Space: O(m^2 + n)
 */
class Solution {
    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        Map<String, Integer> idOf = new HashMap<>();
        for (String s : original) idOf.putIfAbsent(s, idOf.size());
        for (String s : changed) idOf.putIfAbsent(s, idOf.size());
        int m = idOf.size();

        long[][] dist = new long[m][m];
        for (long[] row : dist) Arrays.fill(row, Long.MAX_VALUE / 2);
        for (int i = 0; i < m; i++) dist[i][i] = 0;
        for (int i = 0; i < original.length; i++) {
            int u = idOf.get(original[i]), v = idOf.get(changed[i]);
            dist[u][v] = Math.min(dist[u][v], cost[i]);
        }
        for (int k = 0; k < m; k++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < m; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        Set<Integer> lengths = new TreeSet<>();
        for (String s : idOf.keySet()) lengths.add(s.length());

        int n = source.length();
        long[] dp = new long[n + 1];
        Arrays.fill(dp, Long.MAX_VALUE / 2);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] >= Long.MAX_VALUE / 2) continue;
            if (source.charAt(i) == target.charAt(i)) {
                dp[i + 1] = Math.min(dp[i + 1], dp[i]);
            }
            for (int len : lengths) {
                if (i + len > n) continue;
                Integer u = idOf.get(source.substring(i, i + len));
                Integer v = idOf.get(target.substring(i, i + len));
                if (u != null && v != null && dist[u][v] < Long.MAX_VALUE / 2) {
                    dp[i + len] = Math.min(dp[i + len], dp[i] + dist[u][v]);
                }
            }
        }
        return dp[n] >= Long.MAX_VALUE / 2 ? -1 : dp[n];
    }
}
