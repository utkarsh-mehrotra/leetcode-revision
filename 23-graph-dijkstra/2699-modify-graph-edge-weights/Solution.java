import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * LeetCode 2699. Modify Graph Edge Weights
 * Approach: First, Dijkstra on the graph using only the already-fixed
 * edges (ignoring every -1 edge) FROM the destination gives distToDest[v]
 * -- each node's best-case remaining distance using only known weights.
 * Then Dijkstra from the source, and whenever an edge originally marked
 * -1 is first relaxed, assign it the weight that would make THIS
 * specific path hit target exactly -- target - dist[u] - distToDest[v],
 * floored at 1 -- and use that assigned weight from then on. If the
 * final source-to-destination distance equals target, this succeeded;
 * any -1 edges never used by that search can be set to anything (1).
 * Time: O(E log V) | Space: O(V + E)
 */
class Solution {
    public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
        List<List<long[]>> fixedOnly = new ArrayList<>();
        for (int i = 0; i < n; i++) fixedOnly.add(new ArrayList<>());
        for (int[] e : edges) {
            if (e[2] != -1) {
                fixedOnly.get(e[0]).add(new long[]{e[1], e[2]});
                fixedOnly.get(e[1]).add(new long[]{e[0], e[2]});
            }
        }
        long[] distToDest = dijkstra(fixedOnly, destination, n);

        List<List<int[]>> adjWithEdgeIndex = new ArrayList<>();
        for (int i = 0; i < n; i++) adjWithEdgeIndex.add(new ArrayList<>());
        for (int i = 0; i < edges.length; i++) {
            adjWithEdgeIndex.get(edges[i][0]).add(new int[]{edges[i][1], i});
            adjWithEdgeIndex.get(edges[i][1]).add(new int[]{edges[i][0], i});
        }

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[source] = 0;
        boolean[] done = new boolean[n];
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        pq.add(new long[]{source, 0});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int node = (int) cur[0];
            if (done[node]) continue;
            done[node] = true;
            for (int[] ref : adjWithEdgeIndex.get(node)) {
                int next = ref[0], edgeIdx = ref[1];
                int[] edge = edges[edgeIdx];
                long w = edge[2];
                if (w == -1) {
                    long remaining = (distToDest[next] == Long.MAX_VALUE) ? Long.MAX_VALUE / 4 : distToDest[next];
                    long assigned = Math.max(1, target - dist[node] - remaining);
                    edge[2] = (int) assigned;
                    w = assigned;
                }
                if (!done[next] && dist[node] + w < dist[next]) {
                    dist[next] = dist[node] + w;
                    pq.add(new long[]{next, dist[next]});
                }
            }
        }

        for (int[] e : edges) {
            if (e[2] == -1) e[2] = 1;
        }
        return dist[destination] == target ? edges : new int[0][0];
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
