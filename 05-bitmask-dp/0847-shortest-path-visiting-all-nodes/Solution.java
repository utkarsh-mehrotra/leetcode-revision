import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 847. Shortest Path Visiting All Nodes
 * Approach: Not a natural fit for top-down memoized recursion -- the state
 * graph over (visitedMask, node) is cyclic (you can bounce between already
 * -visited nodes without changing the mask), so a subproblem's answer can
 * depend on another subproblem that depends back on it. That requires a
 * shortest-path search, not a DAG-shaped recursion, so this uses a
 * multi-source BFS over (mask, node) states (every edge has weight 1).
 * Time: O(2^n * n^2) | Space: O(2^n * n)
 */
class Solution {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        if (n == 1) return 0;
        int full = (1 << n) - 1;
        boolean[][] visited = new boolean[1 << n][n];
        Deque<int[]> queue = new ArrayDeque<>(); // {mask, node}
        for (int i = 0; i < n; i++) {
            int mask = 1 << i;
            queue.add(new int[]{mask, i});
            visited[mask][i] = true;
        }
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int[] state = queue.poll();
                int mask = state[0], node = state[1];
                if (mask == full) return steps;
                for (int next : graph[node]) {
                    int nextMask = mask | (1 << next);
                    if (!visited[nextMask][next]) {
                        visited[nextMask][next] = true;
                        queue.add(new int[]{nextMask, next});
                    }
                }
            }
            steps++;
        }
        return -1; // unreachable given problem guarantees a connected graph
    }
}
