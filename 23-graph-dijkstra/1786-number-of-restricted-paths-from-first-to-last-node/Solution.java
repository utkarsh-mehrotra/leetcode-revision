import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * LeetCode 1786. Number of Restricted Paths From First to Last Node
 * Approach: Dijkstra from node n gives every node's shortest distance to
 * n. A "restricted path" must strictly decrease that distance at every
 * step, which guarantees no cycles -- so counting them is a clean
 * top-down memoized recursion from node 1, summing over neighbors with a
 * strictly smaller distance-to-n.
 * Time: O(E log V) | Space: O(V + E)
 */
class Solution {
    private static final int MOD = 1_000_000_007;
    private List<List<int[]>> adj;
    private long[] dist;
    private Integer[] dp;

    public int countRestrictedPaths(int n, int[][] edges) {
        adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(new int[]{e[1], e[2]});
            adj.get(e[1]).add(new int[]{e[0], e[2]});
        }

        dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[n] = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        pq.add(new long[]{n, 0});
        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int node = (int) cur[0];
            long d = cur[1];
            if (d > dist[node]) continue;
            for (int[] edge : adj.get(node)) {
                int next = edge[0];
                long w = edge[1];
                if (dist[node] + w < dist[next]) {
                    dist[next] = dist[node] + w;
                    pq.add(new long[]{next, dist[next]});
                }
            }
        }

        dp = new Integer[n + 1];
        return countPaths(1, n);
    }

    private int countPaths(int node, int n) {
        if (node == n) return 1;
        if (dp[node] != null) return dp[node];
        long total = 0;
        for (int[] edge : adj.get(node)) {
            int next = edge[0];
            if (dist[next] < dist[node]) {
                total = (total + countPaths(next, n)) % MOD;
            }
        }
        dp[node] = (int) total;
        return (int) total;
    }
}
