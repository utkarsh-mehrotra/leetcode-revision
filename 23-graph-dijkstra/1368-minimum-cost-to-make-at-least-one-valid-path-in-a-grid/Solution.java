import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 1368. Minimum Cost to Make at Least One Valid Path in a Grid
 * Approach: 0-1 BFS -- following the cell's existing arrow costs 0 (add
 * to the FRONT of the deque), any other direction costs 1 (add to the
 * BACK). This keeps the deque's front-to-back order monotonic in
 * distance, so the first time a cell is popped its distance is final,
 * exactly like Dijkstra but without needing a priority queue since
 * weights are only 0 or 1.
 * Time: O(rows * cols) | Space: O(rows * cols)
 */
class Solution {
    private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // matches sign values 1,2,3,4

    public int minCost(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int[][] dist = new int[rows][cols];
        for (int[] row : dist) java.util.Arrays.fill(row, Integer.MAX_VALUE);
        dist[0][0] = 0;

        Deque<int[]> deque = new ArrayDeque<>();
        deque.addFirst(new int[]{0, 0});

        while (!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int r = cur[0], c = cur[1];
            for (int dir = 0; dir < 4; dir++) {
                int nr = r + DIRS[dir][0], nc = c + DIRS[dir][1];
                if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) continue;
                int cost = (grid[r][c] - 1 == dir) ? 0 : 1;
                if (dist[r][c] + cost < dist[nr][nc]) {
                    dist[nr][nc] = dist[r][c] + cost;
                    if (cost == 0) deque.addFirst(new int[]{nr, nc});
                    else deque.addLast(new int[]{nr, nc});
                }
            }
        }
        return dist[rows - 1][cols - 1];
    }
}
