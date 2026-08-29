/**
 * LeetCode 1579. Remove Max Number of Edges to Keep Graph Fully Traversable
 * Approach: Two separate Union-Find structures (Alice's, Bob's). Greedily
 * use every type-3 (shared) edge first -- it's never worse to keep a
 * shared edge over a single-owner one, since it helps both parties at
 * once. Then use remaining type-1 edges for Alice and type-2 for Bob.
 * Any edge that doesn't reduce its owner's component count is removable;
 * the graph is fully traversable by both iff each ends with 1 component.
 * Time: O(edges * alpha(n)) | Space: O(n)
 */
class Solution {
    private int[] aliceParent, bobParent;

    public int maxNumEdgesToRemove(int n, int[][] edges) {
        aliceParent = new int[n + 1];
        bobParent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            aliceParent[i] = i;
            bobParent[i] = i;
        }

        int used = 0;
        for (int[] e : edges) {
            if (e[0] == 3) {
                boolean a = union(aliceParent, e[1], e[2]);
                boolean b = union(bobParent, e[1], e[2]);
                if (a || b) used++;
            }
        }
        for (int[] e : edges) {
            if (e[0] == 1 && union(aliceParent, e[1], e[2])) used++;
            if (e[0] == 2 && union(bobParent, e[1], e[2])) used++;
        }

        if (!isFullyConnected(aliceParent, n) || !isFullyConnected(bobParent, n)) return -1;
        return edges.length - used;
    }

    private boolean isFullyConnected(int[] parent, int n) {
        int root = find(parent, 1);
        for (int i = 2; i <= n; i++) {
            if (find(parent, i) != root) return false;
        }
        return true;
    }

    private int find(int[] parent, int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    private boolean union(int[] parent, int a, int b) {
        int ra = find(parent, a), rb = find(parent, b);
        if (ra == rb) return false;
        parent[ra] = rb;
        return true;
    }
}
