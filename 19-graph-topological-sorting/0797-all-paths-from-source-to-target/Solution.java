import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 797. All Paths From Source to Target
 * Approach: The input is already guaranteed a DAG, so plain DFS with
 * backtracking enumerates every source-to-target path without needing a
 * visited set (no cycles to worry about revisiting).
 * Time: O(2^V * V) worst case | Space: O(2^V * V) for the output
 */
class Solution {
    private int[][] graph;
    private int target;
    private List<List<Integer>> result;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        this.graph = graph;
        this.target = graph.length - 1;
        this.result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        dfs(0, path);
        return result;
    }

    private void dfs(int node, List<Integer> path) {
        if (node == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int next : graph[node]) {
            path.add(next);
            dfs(next, path);
            path.remove(path.size() - 1);
        }
    }
}
