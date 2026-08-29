/**
 * LeetCode 2959. Number of Possible Sets of Closing Branches
 * Approach: n is tiny (<=10), so brute-force every subset of branches to
 * KEEP. For each candidate subset, run Floyd-Warshall restricted to
 * roads whose both endpoints are kept, then check every kept pair's
 * shortest distance is within maxDistance.
 * Time: O(2^n * n^3) | Space: O(n^2)
 */
class Solution {
    public int numberOfSets(int n, int maxDistance, int[][] roads) {
        int count = 0;
        for (int mask = 0; mask < (1 << n); mask++) {
            long[][] dist = new long[n][n];
            for (long[] row : dist) java.util.Arrays.fill(row, Long.MAX_VALUE / 2);
            for (int i = 0; i < n; i++) dist[i][i] = 0;
            for (int[] r : roads) {
                int u = r[0], v = r[1], w = r[2];
                if (((mask >> u) & 1) == 0 || ((mask >> v) & 1) == 0) continue;
                dist[u][v] = Math.min(dist[u][v], w);
                dist[v][u] = Math.min(dist[v][u], w);
            }
            for (int k = 0; k < n; k++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (dist[i][k] + dist[k][j] < dist[i][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                        }
                    }
                }
            }

            boolean valid = true;
            for (int i = 0; i < n && valid; i++) {
                if (((mask >> i) & 1) == 0) continue;
                for (int j = 0; j < n; j++) {
                    if (((mask >> j) & 1) == 0) continue;
                    if (dist[i][j] > maxDistance) {
                        valid = false;
                        break;
                    }
                }
            }
            if (valid) count++;
        }
        return count;
    }
}
