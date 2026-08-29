/**
 * LeetCode 785. Is Graph Bipartite?
 * Approach: BFS 2-coloring per connected component -- color the start
 * node, then every neighbor must get the opposite color; finding a
 * neighbor already colored the SAME as the current node means an odd
 * cycle exists, so the graph isn't bipartite.
 * Time: O(V + E) | Space: O(V)
 */
class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n]; // 0 = uncolored, 1 or -1 = the two colors
        for (int start = 0; start < n; start++) {
            if (color[start] != 0) continue;
            color[start] = 1;
            java.util.Deque<Integer> queue = new java.util.ArrayDeque<>();
            queue.add(start);
            while (!queue.isEmpty()) {
                int node = queue.poll();
                for (int next : graph[node]) {
                    if (color[next] == 0) {
                        color[next] = -color[node];
                        queue.add(next);
                    } else if (color[next] == color[node]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
