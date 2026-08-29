import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * LeetCode 2203. Minimum Weighted Subgraph With the Required Paths
 * Approach: Any valid subgraph is a "Y shape" meeting at some node i --
 * a path src1->i, a path src2->i, and a path i->dest. Three Dijkstra
 * runs (forward from src1, forward from src2, and on the reversed graph
 * from dest) give every node's distance along each of those three legs;
 * the answer is the min total over every candidate meeting point i.
 * Time: O(E log V) | Space: O(V + E)
 */
class Solution {
    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        List<List<long[]>> fwd = new ArrayList<>();
        List<List<long[]>> rev = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            fwd.add(new ArrayList<>());
            rev.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            fwd.get(e[0]).add(new long[]{e[1], e[2]});
            rev.get(e[1]).add(new long[]{e[0], e[2]});
        }

        long[] d1 = dijkstra(fwd, src1, n);
        long[] d2 = dijkstra(fwd, src2, n);
        long[] d3 = dijkstra(rev, dest, n);

        long best = -1;
        for (int i = 0; i < n; i++) {
            if (d1[i] == Long.MAX_VALUE || d2[i] == Long.MAX_VALUE || d3[i] == Long.MAX_VALUE) continue;
            long total = d1[i] + d2[i] + d3[i];
            if (best == -1 || total < best) best = total;
        }
        return best;
    }

    private long[] dijkstra(List<List<long[]>> adj, int src, int n) {
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[src] = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        pq.add(new long[]{src, 0});
        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int node = (int) cur[0];
            long d = cur[1];
            if (d > dist[node]) continue;
            for (long[] edge : adj.get(node)) {
                int next = (int) edge[0];
                long w = edge[1];
                if (dist[node] + w < dist[next]) {
                    dist[next] = dist[node] + w;
                    pq.add(new long[]{next, dist[next]});
                }
            }
        }
        return dist;
    }
}
