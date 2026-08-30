import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 1377. Frog Position After T Seconds
 * Approach: The edge list describes an undirected tree rooted at vertex
 * 1. DFS from the root tracking remaining time; each second the frog is
 * forced to jump to one of its unvisited children (uniformly), or
 * freezes in place forever once it has none left. The frog only "counts"
 * as being at target at the terminal moment -- time exhausted or stuck
 * at a leaf -- so passing through target early with children still
 * unvisited correctly contributes 0 (the frog is forced to move on).
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public double frogPosition(int n, int[][] edges, int t, int target) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }
        boolean[] visited = new boolean[n + 1];
        return dfs(graph, visited, 1, t, target);
    }

    private double dfs(Map<Integer, List<Integer>> graph, boolean[] visited, int node, int t, int target) {
        visited[node] = true;
        List<Integer> unvisitedNeighbors = new ArrayList<>();
        for (int neighbor : graph.getOrDefault(node, List.of())) {
            if (!visited[neighbor]) unvisitedNeighbors.add(neighbor);
        }
        if (t == 0 || unvisitedNeighbors.isEmpty()) {
            return node == target ? 1.0 : 0.0;
        }
        for (int neighbor : unvisitedNeighbors) {
            double result = dfs(graph, visited, neighbor, t - 1, target);
            if (result > 0) return result / unvisitedNeighbors.size();
        }
        return 0.0;
    }
}
