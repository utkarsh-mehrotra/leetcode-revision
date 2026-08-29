import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * LeetCode 882. Reachable Nodes in Subdivided Graph
 * Approach: Run Dijkstra on the ORIGINAL (unsubdivided) graph, where
 * edge (u,v) with cnt subdivisions has weight cnt+1 (the full hop
 * distance through all its subdivided nodes). An original node is
 * reachable iff its distance is <= maxMoves. For an edge not fully
 * traversable, count how many of its subdivided nodes are reachable
 * from each end (bounded by leftover budget after reaching that end),
 * capped so the two ends' counts never overlap past the edge's own length.
 * Time: O(E log V) | Space: O(V + E)
 */
class Solution {
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(new int[]{e[1], e[2]});
            adj.get(e[1]).add(new int[]{e[0], e[2]});
        }

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{0, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0], d = cur[1];
            if (d > dist[node]) continue;
            for (int[] edge : adj.get(node)) {
                int next = edge[0], weight = edge[1] + 1;
                if (dist[node] + weight < dist[next]) {
                    dist[next] = dist[node] + weight;
                    pq.add(new int[]{next, dist[next]});
                }
            }
        }

        int reachableOriginal = 0;
        for (int i = 0; i < n; i++) {
            if (dist[i] <= maxMoves) reachableOriginal++;
        }

        int reachableSubdivided = 0;
        for (int[] e : edges) {
            int u = e[0], v = e[1], cnt = e[2];
            int usedFromU = dist[u] < maxMoves ? Math.min(cnt, maxMoves - dist[u]) : 0;
            int usedFromV = dist[v] < maxMoves ? Math.min(cnt, maxMoves - dist[v]) : 0;
            reachableSubdivided += Math.min(cnt, usedFromU + usedFromV);
        }
        return reachableOriginal + reachableSubdivided;
    }
}
