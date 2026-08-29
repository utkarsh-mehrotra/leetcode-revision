import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 2290. Minimum Obstacle Removal to Reach Corner
 * Approach: 0-1 BFS -- moving into an empty cell costs 0 (pushed to the
 * FRONT of the deque), moving into an obstacle costs 1 (pushed to the
 * BACK), keeping the deque's distances monotonic without needing a
 * priority queue.
 * Time: O(rows * cols) | Space: O(rows * cols)
 */
class Solution {
    public int minimumObstacles(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int[][] dist = new int[rows][cols];
        for (int[] row : dist) java.util.Arrays.fill(row, Integer.MAX_VALUE);
        dist[0][0] = 0;

        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Deque<int[]> deque = new ArrayDeque<>();
        deque.addFirst(new int[]{0, 0});

        while (!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int r = cur[0], c = cur[1];
            for (int[] dir : dirs) {
                int nr = r + dir[0], nc = c + dir[1];
                if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) continue;
                int cost = grid[nr][nc];
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
