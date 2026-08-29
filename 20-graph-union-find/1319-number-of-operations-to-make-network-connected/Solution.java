/**
 * LeetCode 1319. Number of Operations to Make Network Connected
 * Approach: A spanning connection needs at least n-1 cables total; fewer
 * makes it impossible regardless of rearrangement. Otherwise, Union-Find
 * over the existing cables finds the number of connected components;
 * connecting them all needs exactly (components - 1) moves (each move
 * repurposes one redundant cable -- of which there are always at least
 * components - 1 available once the edge-count check passes).
 * Time: O(connections * alpha(n)) | Space: O(n)
 */
class Solution {
    private int[] parent;

    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) return -1;
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        for (int[] c : connections) {
            union(c[0], c[1]);
        }
        int components = 0;
        for (int i = 0; i < n; i++) {
            if (find(i) == i) components++;
        }
        return components - 1;
    }

    private int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    private void union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra != rb) parent[ra] = rb;
    }
}
