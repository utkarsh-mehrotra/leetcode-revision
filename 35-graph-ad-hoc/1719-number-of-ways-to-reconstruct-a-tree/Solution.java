import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * LeetCode 1719. Number of Ways to Reconstruct a Tree
 * Approach: `pairs` lists every (ancestor, descendant) pair from some
 * unknown rooted tree, so for a node v, adj[v] (built from the pairs) is
 * exactly the set of ALL its ancestors and descendants combined. That
 * makes adj[v] shrink monotonically down each root-to-leaf path: a node's
 * parent must be the neighbor with the SMALLEST adjacency set that is
 * still a superset of v's own set (the closest ancestor). Process nodes
 * in decreasing set-size order so every candidate parent is already
 * placed, pick that minimal superset neighbor as parent, and verify the
 * subset relationship holds exactly (otherwise no valid tree exists). A
 * tie in set size between a node and its chosen parent means their
 * roles are interchangeable -- multiple valid trees exist.
 * Time: O(n^2) | Space: O(n^2)
 */
class Solution {
    public int checkWays(int[][] pairs) {
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        for (int[] p : pairs) {
            adj.computeIfAbsent(p[0], k -> new HashSet<>()).add(p[1]);
            adj.computeIfAbsent(p[1], k -> new HashSet<>()).add(p[0]);
        }
        int n = adj.size();

        List<Integer> nodes = new ArrayList<>(adj.keySet());
        nodes.sort((a, b) -> adj.get(b).size() - adj.get(a).size());

        // The root must be connected to every other node.
        if (adj.get(nodes.get(0)).size() != n - 1) return 0;

        Set<Integer> placed = new HashSet<>();
        placed.add(nodes.get(0));
        boolean ambiguous = false;

        for (int idx = 1; idx < nodes.size(); idx++) {
            int cur = nodes.get(idx);
            Set<Integer> curAdj = adj.get(cur);

            int parent = -1;
            int parentSize = Integer.MAX_VALUE;
            for (int candidate : curAdj) {
                if (placed.contains(candidate) && adj.get(candidate).size() >= curAdj.size()
                        && adj.get(candidate).size() < parentSize) {
                    parentSize = adj.get(candidate).size();
                    parent = candidate;
                }
            }
            if (parent == -1) return 0;

            // Every other node in curAdj must also be reachable via the parent's set.
            for (int node : curAdj) {
                if (node != parent && !adj.get(parent).contains(node)) return 0;
            }

            if (adj.get(parent).size() == curAdj.size()) ambiguous = true;
            placed.add(cur);
        }

        return ambiguous ? 2 : 1;
    }
}
