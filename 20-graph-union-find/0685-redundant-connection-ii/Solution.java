/**
 * LeetCode 685. Redundant Connection II
 * Approach: A valid rooted tree gives every node exactly one parent. If
 * some node v gets a second incoming edge while scanning, its two
 * candidate "extra" edges are recorded and the second one is tentatively
 * removed. Union-Find then checks the rest of the edges (skipping that
 * removed one) for a cycle: if a cycle is still found, the removed edge
 * was actually needed and the FIRST candidate is the true answer;
 * otherwise the removed (second) candidate is the answer. If no node
 * ever had two parents, this degenerates to the plain single-cycle case.
 * Time: O(n * alpha(n)) | Space: O(n)
 */
class Solution {
    private int[] parent;

    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] parentOf = new int[n + 1];
        int[] candidate1 = null, candidate2 = null;

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (parentOf[v] == 0) {
                parentOf[v] = u;
            } else {
                candidate1 = new int[]{parentOf[v], v};
                candidate2 = new int[]{u, v};
                edge[1] = 0; // mark this edge to be skipped below
                break;
            }
        }

        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) parent[i] = i;

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (v == 0) continue; // the marked, tentatively-removed edge
            int ru = find(u), rv = find(v);
            if (ru == rv) {
                return candidate1 == null ? edge : candidate1;
            }
            parent[ru] = rv;
        }
        return candidate2;
    }

    private int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
}
