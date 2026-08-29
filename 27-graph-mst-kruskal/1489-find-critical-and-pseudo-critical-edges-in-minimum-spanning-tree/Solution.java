import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 1489. Find Critical and Pseudo-Critical Edges in Minimum Spanning Tree
 * Approach: Kruskal's algorithm gives the baseline MST weight. For each
 * edge, re-run Kruskal's twice: once EXCLUDING it (if the result is
 * heavier than baseline, or no spanning tree forms at all, the edge is
 * critical -- every MST needs it), and once FORCING it in first (if that
 * still reaches baseline weight, the edge is pseudo-critical -- SOME MST
 * can use it, just not a strict requirement).
 * Time: O(E^2 * alpha(V)) | Space: O(V + E)
 */
class Solution {
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int m = edges.length;
        Integer[] order = new Integer[m];
        for (int i = 0; i < m; i++) order[i] = i;
        java.util.Arrays.sort(order, (a, b) -> edges[a][2] - edges[b][2]);

        int baseline = mstWeight(n, edges, order, -1, -1);

        List<Integer> critical = new ArrayList<>();
        List<Integer> pseudoCritical = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int withoutEdge = mstWeight(n, edges, order, i, -1);
            if (withoutEdge > baseline) {
                critical.add(i);
            } else {
                int forcedEdge = mstWeight(n, edges, order, -1, i);
                if (forcedEdge == baseline) {
                    pseudoCritical.add(i);
                }
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(critical);
        result.add(pseudoCritical);
        return result;
    }

    // MST weight over `edges` (visited in `order`), optionally skipping one edge
    // index or forcing another in before the rest. Returns a huge value if no
    // spanning tree results (skip case only).
    private int mstWeight(int n, int[][] edges, Integer[] order, int skipIdx, int forceIdx) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        int weight = 0, used = 0;
        if (forceIdx != -1) {
            int[] e = edges[forceIdx];
            union(parent, e[0], e[1]);
            weight += e[2];
            used++;
        }
        for (int idx : order) {
            if (idx == skipIdx || idx == forceIdx) continue;
            int[] e = edges[idx];
            if (find(parent, e[0]) != find(parent, e[1])) {
                union(parent, e[0], e[1]);
                weight += e[2];
                used++;
            }
        }
        return used == n - 1 ? weight : Integer.MAX_VALUE;
    }

    private int find(int[] parent, int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    private void union(int[] parent, int a, int b) {
        int ra = find(parent, a), rb = find(parent, b);
        if (ra != rb) parent[ra] = rb;
    }
}
