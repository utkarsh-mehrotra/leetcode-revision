import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 1971. Find if Path Exists in Graph
 * Approach: Plain BFS from source over the adjacency list; destination is
 * reachable iff BFS visits it.
 * Time: O(V + E) | Space: O(V + E)
 */
class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        boolean[] visited = new boolean[n];
        java.util.Deque<Integer> queue = new java.util.ArrayDeque<>();
        queue.add(source);
        visited[source] = true;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (node == destination) return true;
            for (int next : adj.get(node)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
        return false;
    }
}
