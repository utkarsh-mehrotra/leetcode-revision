/**
 * LeetCode 1584. Min Cost to Connect All Points
 * Approach: Kruskal's algorithm -- generate every pairwise Manhattan-
 * distance edge, sort by weight, and greedily union-find them in,
 * skipping any that would close a cycle, until every point is connected.
 * Time: O(n^2 log n) | Space: O(n^2)
 */
class Solution {
    private int[] parent;

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int[][] edges = new int[n * (n - 1) / 2][3];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int dist = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                edges[idx++] = new int[]{i, j, dist};
            }
        }
        java.util.Arrays.sort(edges, (a, b) -> a[2] - b[2]);

        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        int totalCost = 0, used = 0;
        for (int[] e : edges) {
            if (find(e[0]) != find(e[1])) {
                union(e[0], e[1]);
                totalCost += e[2];
                used++;
                if (used == n - 1) break;
            }
        }
        return totalCost;
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
