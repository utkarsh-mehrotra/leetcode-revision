import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 310. Minimum Height Trees
 * Approach: Multi-source BFS peeling leaves layer by layer -- the
 * minimum-height root(s) are whatever remains after repeatedly stripping
 * every current leaf, since a tree's center (1 or 2 nodes) is exactly
 * the last node(s) left standing under this "trim from the outside in" process.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            List<Integer> single = new ArrayList<>();
            single.add(0);
            return single;
        }

        List<List<Integer>> adj = new ArrayList<>();
        int[] degree = new int[n];
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
            degree[e[0]]++;
            degree[e[1]]++;
        }

        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) leaves.add(i);
        }

        int remaining = n;
        while (remaining > 2) {
            remaining -= leaves.size();
            List<Integer> nextLeaves = new ArrayList<>();
            for (int leaf : leaves) {
                for (int neighbor : adj.get(leaf)) {
                    if (--degree[neighbor] == 1) nextLeaves.add(neighbor);
                }
            }
            leaves = nextLeaves;
        }
        return leaves;
    }
}
