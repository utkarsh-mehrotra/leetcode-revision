import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LeetCode 2213. Strange Printer II
 * Approach: Every printed color forms one axis-aligned solid rectangle,
 * later prints painting over earlier ones. For each color, compute its
 * bounding box over the target grid -- any OTHER color found inside that
 * box must have been printed strictly after this color (it's painted on
 * top within the box). That gives a "must print before" edge
 * color -> otherColor; the grid is achievable iff this dependency graph
 * has no cycle (checked via Kahn's algorithm).
 * Time: O(rows * cols * colors) worst case | Space: O(colors^2)
 */
class Solution {
    public boolean isPrintable(int[][] targetGrid) {
        int rows = targetGrid.length, cols = targetGrid[0].length;
        int maxColor = 0;
        for (int[] row : targetGrid) {
            for (int c : row) maxColor = Math.max(maxColor, c);
        }

        int[] minRow = new int[maxColor + 1], maxRow = new int[maxColor + 1];
        int[] minCol = new int[maxColor + 1], maxCol = new int[maxColor + 1];
        Arrays.fill(minRow, Integer.MAX_VALUE);
        Arrays.fill(minCol, Integer.MAX_VALUE);
        Arrays.fill(maxRow, Integer.MIN_VALUE);
        Arrays.fill(maxCol, Integer.MIN_VALUE);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int color = targetGrid[r][c];
                minRow[color] = Math.min(minRow[color], r);
                maxRow[color] = Math.max(maxRow[color], r);
                minCol[color] = Math.min(minCol[color], c);
                maxCol[color] = Math.max(maxCol[color], c);
            }
        }

        List<Set<Integer>> adj = new ArrayList<>();
        int[] indegree = new int[maxColor + 1];
        for (int i = 0; i <= maxColor; i++) adj.add(new HashSet<>());

        int totalColors = 0;
        for (int color = 1; color <= maxColor; color++) {
            if (minRow[color] > maxRow[color]) continue; // color absent
            totalColors++;
            for (int r = minRow[color]; r <= maxRow[color]; r++) {
                for (int c = minCol[color]; c <= maxCol[color]; c++) {
                    int other = targetGrid[r][c];
                    if (other != color && adj.get(color).add(other)) {
                        indegree[other]++;
                    }
                }
            }
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for (int color = 1; color <= maxColor; color++) {
            if (minRow[color] <= maxRow[color] && indegree[color] == 0) queue.add(color);
        }
        int visited = 0;
        while (!queue.isEmpty()) {
            int color = queue.poll();
            visited++;
            for (int next : adj.get(color)) {
                if (--indegree[next] == 0) queue.add(next);
            }
        }
        return visited == totalColors;
    }
}
