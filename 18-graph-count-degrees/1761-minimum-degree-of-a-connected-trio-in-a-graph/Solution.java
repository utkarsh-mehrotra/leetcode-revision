/**
 * LeetCode 1761. Minimum Degree of a Connected Trio in a Graph
 * Approach: For every trio of mutually connected nodes (checked via an
 * adjacency matrix), its degree is the sum of the three nodes' degrees
 * minus 6 -- each of the trio's 3 internal edges is counted twice (once
 * per endpoint) among those degree sums, and none of those 6 counts
 * should be included since they're internal to the trio.
 * Time: O(n^3) | Space: O(n^2)
 */
class Solution {
    public int minTrioDegree(int n, int[][] edges) {
        boolean[][] adj = new boolean[n + 1][n + 1];
        int[] degree = new int[n + 1];
        for (int[] e : edges) {
            adj[e[0]][e[1]] = true;
            adj[e[1]][e[0]] = true;
            degree[e[0]]++;
            degree[e[1]]++;
        }

        int best = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (!adj[i][j]) continue;
                for (int k = j + 1; k <= n; k++) {
                    if (adj[i][k] && adj[j][k]) {
                        best = Math.min(best, degree[i] + degree[j] + degree[k] - 6);
                    }
                }
            }
        }
        return best == Integer.MAX_VALUE ? -1 : best;
    }
}
