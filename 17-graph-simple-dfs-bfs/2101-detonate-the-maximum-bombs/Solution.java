import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 2101. Detonate the Maximum Bombs
 * Approach: Build a directed graph where bomb i -> bomb j if j lies
 * within i's blast radius (detonating i also triggers j). For each
 * candidate starting bomb, DFS/BFS counts how many bombs its chain
 * reaction reaches; the answer is the best over all starting points.
 * Time: O(n^2) building the graph + O(n^2) total traversal | Space: O(n^2)
 */
class Solution {
    public int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < n; i++) {
            long xi = bombs[i][0], yi = bombs[i][1], ri = bombs[i][2];
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                long dx = xi - bombs[j][0], dy = yi - bombs[j][1];
                if (dx * dx + dy * dy <= ri * ri) {
                    adj.get(i).add(j);
                }
            }
        }

        int best = 0;
        for (int start = 0; start < n; start++) {
            boolean[] visited = new boolean[n];
            best = Math.max(best, dfs(adj, start, visited));
        }
        return best;
    }

    private int dfs(List<List<Integer>> adj, int node, boolean[] visited) {
        visited[node] = true;
        int count = 1;
        for (int next : adj.get(node)) {
            if (!visited[next]) {
                count += dfs(adj, next, visited);
            }
        }
        return count;
    }
}
