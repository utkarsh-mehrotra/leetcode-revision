import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 399. Evaluate Division
 * Approach: Build a weighted directed graph from each equation A/B=v --
 * an edge A->B with weight v and B->A with weight 1/v. Each query is
 * answered by a DFS from the numerator toward the denominator,
 * multiplying edge weights along the way; an unreachable pair (or an
 * unknown variable) answers -1.0.
 * Time: O(equations + queries * V) | Space: O(V + E)
 */
class Solution {
    private Map<String, List<double[]>> adj; // node -> list of {neighborIndexAsCode, weight} via parallel map

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0), b = equations.get(i).get(1);
            graph.computeIfAbsent(a, k -> new HashMap<>()).put(b, values[i]);
            graph.computeIfAbsent(b, k -> new HashMap<>()).put(a, 1.0 / values[i]);
        }

        double[] answers = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String src = queries.get(i).get(0), dst = queries.get(i).get(1);
            if (!graph.containsKey(src) || !graph.containsKey(dst)) {
                answers[i] = -1.0;
            } else if (src.equals(dst)) {
                answers[i] = 1.0;
            } else {
                Double result = dfs(graph, src, dst, new java.util.HashSet<>());
                answers[i] = (result == null) ? -1.0 : result;
            }
        }
        return answers;
    }

    private Double dfs(Map<String, Map<String, Double>> graph, String node, String target, java.util.Set<String> visited) {
        visited.add(node);
        for (Map.Entry<String, Double> edge : graph.get(node).entrySet()) {
            String next = edge.getKey();
            if (visited.contains(next)) continue;
            if (next.equals(target)) return edge.getValue();
            Double sub = dfs(graph, next, target, visited);
            if (sub != null) return edge.getValue() * sub;
        }
        return null;
    }
}
