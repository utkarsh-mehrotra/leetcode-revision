import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 1129. Shortest Path with Alternating Colors
 * Approach: All edges have equal weight, so a relaxation-style multi-
 * round BFS suffices -- state is (node, colorOfEdgeJustUsed). The start
 * node is seeded as reachable via "both colors" at distance 0 (so either
 * color can legally begin a path), and every move must use the OTHER
 * color from the previous edge.
 * Time: O(V + E) | Space: O(V + E)
 */
class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<List<int[]>> adj = new ArrayList<>(); // {node, color}: color 0 = red, 1 = blue
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : redEdges) adj.get(e[0]).add(new int[]{e[1], 0});
        for (int[] e : blueEdges) adj.get(e[0]).add(new int[]{e[1], 1});

        int[][] dist = new int[n][2];
        for (int[] row : dist) Arrays.fill(row, -1);
        boolean[][] visited = new boolean[n][2];
        dist[0][0] = 0;
        dist[0][1] = 0;
        visited[0][0] = true;
        visited[0][1] = true;

        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});
        queue.add(new int[]{0, 1});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int node = cur[0], color = cur[1];
            for (int[] edge : adj.get(node)) {
                int next = edge[0], edgeColor = edge[1];
                if (edgeColor == color || visited[next][edgeColor]) continue;
                visited[next][edgeColor] = true;
                dist[next][edgeColor] = dist[node][color] + 1;
                queue.add(new int[]{next, edgeColor});
            }
        }

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int r = dist[i][0], b = dist[i][1];
            if (r == -1) result[i] = b;
            else if (b == -1) result[i] = r;
            else result[i] = Math.min(r, b);
        }
        result[0] = 0;
        return result;
    }
}
