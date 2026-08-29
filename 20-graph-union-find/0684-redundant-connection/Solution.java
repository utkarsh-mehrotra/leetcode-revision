/**
 * LeetCode 684. Redundant Connection
 * Approach: Union-Find over the edges in order -- a tree on n nodes has
 * exactly n-1 edges, so the first edge whose two endpoints are already in
 * the same component is the one extra edge that creates the cycle.
 * Time: O(n * alpha(n)) | Space: O(n)
 */
class Solution {
    private int[] parent;

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) parent[i] = i;

        for (int[] edge : edges) {
            int a = find(edge[0]), b = find(edge[1]);
            if (a == b) return edge;
            parent[a] = b;
        }
        return new int[0];
    }

    private int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
}
