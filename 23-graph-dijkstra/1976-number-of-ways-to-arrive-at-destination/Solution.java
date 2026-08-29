import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * LeetCode 1976. Number of Ways to Arrive at Destination
 * Approach: Dijkstra tracking a ways[] count alongside dist[] -- when a
 * shorter path to a node is found, its ways count resets to match
 * whatever path just beat it; when a path TIES the current shortest
 * distance, its ways count adds in (a second, equally-short route).
 * Time: O(E log V) | Space: O(V + E)
 */
class Solution {
    private static final int MOD = 1_000_000_007;

    public int countPaths(int n, int[][] roads) {
        List<List<long[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] r : roads) {
            adj.get(r[0]).add(new long[]{r[1], r[2]});
            adj.get(r[1]).add(new long[]{r[0], r[2]});
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        long[] ways = new long[n];
        ways[0] = 1;
        boolean[] done = new boolean[n];

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        pq.add(new long[]{0, 0});
        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int node = (int) cur[0];
            if (done[node]) continue;
            done[node] = true;
            for (long[] edge : adj.get(node)) {
                int next = (int) edge[0];
                long w = edge[1];
                if (dist[node] + w < dist[next]) {
                    dist[next] = dist[node] + w;
                    ways[next] = ways[node];
                    pq.add(new long[]{next, dist[next]});
                } else if (dist[node] + w == dist[next]) {
                    ways[next] = (ways[next] + ways[node]) % MOD;
                }
            }
        }
        return (int) (ways[n - 1] % MOD);
    }
}
