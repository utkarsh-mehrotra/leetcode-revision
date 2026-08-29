/**
 * LeetCode 2976. Minimum Cost to Convert String I
 * Approach: Floyd-Warshall over the 26-letter alphabet -- build a
 * min-cost adjacency matrix from every (original[i], changed[i], cost[i])
 * transformation (keeping only the cheapest if repeated), then compute
 * all-pairs shortest transformation costs. Sum the per-position cost for
 * every differing character; any position with no route makes the whole
 * answer -1.
 * Time: O(26^3 + n) | Space: O(26^2)
 */
class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        long[][] dist = new long[26][26];
        for (long[] row : dist) java.util.Arrays.fill(row, Long.MAX_VALUE / 2);
        for (int i = 0; i < 26; i++) dist[i][i] = 0;
        for (int i = 0; i < original.length; i++) {
            int u = original[i] - 'a', v = changed[i] - 'a';
            dist[u][v] = Math.min(dist[u][v], cost[i]);
        }

        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        long total = 0;
        for (int i = 0; i < source.length(); i++) {
            int u = source.charAt(i) - 'a', v = target.charAt(i) - 'a';
            if (u == v) continue;
            if (dist[u][v] >= Long.MAX_VALUE / 2) return -1;
            total += dist[u][v];
        }
        return total;
    }
}
