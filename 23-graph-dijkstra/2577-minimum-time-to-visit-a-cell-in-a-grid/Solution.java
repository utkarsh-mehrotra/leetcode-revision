import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * LeetCode 2577. Minimum Time to Visit a Cell in a Grid
 * Approach: Dijkstra over grid cells -- if a neighbor's required
 * unlock-time exceeds our natural arrival time, we can always wait by
 * bouncing between two cells (each bounce costs 2 minutes), so the
 * actual arrival time is bumped up to the smallest value >= the
 * requirement that has the SAME PARITY as the requirement (since
 * bouncing changes arrival time by increments of 2). If both of start's
 * neighbors already require time > 1, no bounce is possible at the very
 * first move and the grid is unsolvable.
 * Time: O(rows * cols * log(rows * cols)) | Space: O(rows * cols)
 */
class Solution {
    public int minimumTime(int[][] grid) {
        if (grid[0][1] > 1 && grid[1][0] > 1) return -1;
        int rows = grid.length, cols = grid[0].length;
        int[][] dist = new int[rows][cols];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        dist[0][0] = 0;

        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.add(new int[]{0, 0, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int r = cur[0], c = cur[1], d = cur[2];
            if (d > dist[r][c]) continue;
            if (r == rows - 1 && c == cols - 1) return d;
            for (int[] dir : dirs) {
                int nr = r + dir[0], nc = c + dir[1];
                if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) continue;
                int arrival = d + 1;
                if (arrival < grid[nr][nc]) {
                    int diff = grid[nr][nc] - arrival;
                    arrival = (diff % 2 == 0) ? grid[nr][nc] + 1 : grid[nr][nc];
                }
                if (arrival < dist[nr][nc]) {
                    dist[nr][nc] = arrival;
                    pq.add(new int[]{nr, nc, arrival});
                }
            }
        }
        return -1;
    }
}
