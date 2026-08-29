import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 2065. Maximum Path Quality of a Graph
 * Approach: NOT a memoization fit -- the value of a state depends on
 * exactly WHICH nodes have already been visited (each node's value counts
 * only once), so the reusable-subproblem property memoization relies on
 * doesn't hold; two different paths that reach the same node with the same
 * remaining time can still have different future values depending on
 * which nodes are already "spent". This is instead solved with DFS +
 * backtracking: walk edges from node 0, add a node's value the first time
 * it's visited (un-add on backtrack), and whenever back at node 0 with
 * time to spare, record the best quality seen. The problem guarantees at
 * most 4 edges per node specifically so this exponential search stays
 * small in practice despite n being up to 1000.
 * Time: O(4^(maxTime/minEdgeTime)) | Space: O(n)
 */
class Solution {
    private int best = 0;
    private List<List<int[]>> graph;
    private int[] values;
    private boolean[] visited;

    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
        int n = values.length;
        this.values = values;
        this.visited = new boolean[n];
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] e : edges) {
            graph.get(e[0]).add(new int[]{e[1], e[2]});
            graph.get(e[1]).add(new int[]{e[0], e[2]});
        }

        visited[0] = true;
        dfs(0, maxTime, values[0]);
        return best;
    }

    private void dfs(int node, int timeLeft, int quality) {
        if (node == 0) best = Math.max(best, quality);
        for (int[] edge : graph.get(node)) {
            int next = edge[0], cost = edge[1];
            if (cost > timeLeft) continue;
            boolean firstVisit = !visited[next];
            if (firstVisit) visited[next] = true;
            dfs(next, timeLeft - cost, quality + (firstVisit ? values[next] : 0));
            if (firstVisit) visited[next] = false;
        }
    }
}
