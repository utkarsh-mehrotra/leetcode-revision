/**
 * LeetCode 2642. Design Graph With Shortest Path Calculator
 * Approach: Maintain the full Floyd-Warshall all-pairs distance matrix.
 * Adding one new edge u->v with weight w can only help paths that route
 * through it, so instead of rebuilding from scratch, relax every pair
 * (i,j) via dist[i][u] + w + dist[v][j] -- equivalent to one extra
 * Floyd-Warshall "k" pass using only the two new endpoints.
 * Time: O(n^2) per addEdge, O(1) per shortestPath | Space: O(n^2)
 */
class Graph {
    private int n;
    private long[][] dist;

    public Graph(int n, int[][] edges) {
        this.n = n;
        dist = new long[n][n];
        for (long[] row : dist) java.util.Arrays.fill(row, Long.MAX_VALUE / 2);
        for (int i = 0; i < n; i++) dist[i][i] = 0;
        for (int[] e : edges) {
            dist[e[0]][e[1]] = Math.min(dist[e[0]][e[1]], e[2]);
        }
        floydWarshall();
    }

    public void addEdge(int[] edge) {
        int u = edge[0], v = edge[1], w = edge[2];
        if (w >= dist[u][v]) return; // no improvement possible
        dist[u][v] = w;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                long viaNewEdge = dist[i][u] + w + dist[v][j];
                if (viaNewEdge < dist[i][j]) {
                    dist[i][j] = viaNewEdge;
                }
            }
        }
    }

    public int shortestPath(int node1, int node2) {
        long d = dist[node1][node2];
        return d >= Long.MAX_VALUE / 2 ? -1 : (int) d;
    }

    private void floydWarshall() {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
    }
}
