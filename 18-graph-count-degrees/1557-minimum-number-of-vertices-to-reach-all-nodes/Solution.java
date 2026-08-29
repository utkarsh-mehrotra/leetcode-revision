import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 1557. Minimum Number of Vertices to Reach All Nodes
 * Approach: In a DAG, a node with indegree 0 can't be reached from any
 * other node, so it MUST be a starting point; conversely every node with
 * indegree >= 1 is reachable from some other node along its incoming
 * edge, so the full set of indegree-0 nodes is both necessary and
 * sufficient.
 * Time: O(n + edges) | Space: O(n)
 */
class Solution {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        boolean[] hasIncoming = new boolean[n];
        for (List<Integer> edge : edges) {
            hasIncoming[edge.get(1)] = true;
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!hasIncoming[i]) result.add(i);
        }
        return result;
    }
}
