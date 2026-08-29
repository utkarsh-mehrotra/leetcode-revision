import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 1857. Largest Color Value in a Directed Graph
 * Approach: Kahn's algorithm topological sort doubles as cycle detection
 * (if fewer than n nodes get processed, a cycle exists, making the path
 * value unbounded -> -1). While processing in topological order,
 * propagate each node's per-color max count forward to its successors,
 * tracking the best count seen.
 * Time: O(V + E) | Space: O(V * 26)
 */
class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        List<List<Integer>> adj = new ArrayList<>();
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            indegree[e[1]]++;
        }

        int[][] count = new int[n][26];
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) queue.add(i);
        }

        int visited = 0, best = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            visited++;
            count[node][colors.charAt(node) - 'a']++;
            for (int c = 0; c < 26; c++) best = Math.max(best, count[node][c]);
            for (int next : adj.get(node)) {
                for (int c = 0; c < 26; c++) {
                    count[next][c] = Math.max(count[next][c], count[node][c]);
                }
                if (--indegree[next] == 0) queue.add(next);
            }
        }
        return visited == n ? best : -1;
    }
}
