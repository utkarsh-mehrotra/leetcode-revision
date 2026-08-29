import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 2608. Shortest Cycle in a Graph
 * Approach: BFS from every node as a potential cycle "top" -- whenever
 * BFS reaches an already-visited neighbor that ISN'T the parent edge
 * just used, the two branches meeting there close a cycle of length
 * dist[u] + dist[v] + 1. Trying every source and taking the minimum
 * such closure finds the shortest cycle in the whole graph.
 * Time: O(V * (V + E)) | Space: O(V + E)
 */
class Solution {
    public int findShortestCycle(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        int best = Integer.MAX_VALUE;
        for (int src = 0; src < n; src++) {
            int[] dist = new int[n];
            int[] parent = new int[n];
            Arrays.fill(dist, -1);
            Arrays.fill(parent, -1);
            dist[src] = 0;
            Deque<Integer> queue = new ArrayDeque<>();
            queue.add(src);
            while (!queue.isEmpty()) {
                int node = queue.poll();
                for (int next : adj.get(node)) {
                    if (dist[next] == -1) {
                        dist[next] = dist[node] + 1;
                        parent[next] = node;
                        queue.add(next);
                    } else if (next != parent[node]) {
                        best = Math.min(best, dist[node] + dist[next] + 1);
                    }
                }
            }
        }
        return best == Integer.MAX_VALUE ? -1 : best;
    }
}
