import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * LeetCode 1632. Rank Transform of a Matrix
 * Approach: Process cell values in increasing order. Within a single
 * value's group, Union-Find merges cells that share a row or column
 * (they're forced to receive the same rank). Each resulting group's rank
 * is 1 + the max rank already achieved (from strictly smaller values) in
 * any row or column the group touches; that rank is then assigned to
 * every cell in the group and folded back into those rows'/columns'
 * running max-rank trackers before moving to the next value.
 * Time: O(rows*cols*log(rows*cols)) | Space: O(rows*cols)
 */
class Solution {
    public int[][] matrixRankTransform(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        int[] rowMaxRank = new int[rows];
        int[] colMaxRank = new int[cols];
        int[][] result = new int[rows][cols];

        TreeMap<Integer, List<int[]>> cellsByValue = new TreeMap<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                cellsByValue.computeIfAbsent(matrix[r][c], k -> new ArrayList<>()).add(new int[]{r, c});
            }
        }

        for (List<int[]> cells : cellsByValue.values()) {
            int[] parent = new int[rows + cols];
            for (int i = 0; i < rows + cols; i++) parent[i] = i;
            for (int[] cell : cells) {
                union(parent, cell[0], rows + cell[1]);
            }

            Map<Integer, Integer> groupMaxRank = new HashMap<>();
            for (int[] cell : cells) {
                int root = find(parent, cell[0]);
                int currentMax = Math.max(rowMaxRank[cell[0]], colMaxRank[cell[1]]);
                groupMaxRank.merge(root, currentMax, Math::max);
            }
            for (int[] cell : cells) {
                int root = find(parent, cell[0]);
                int rank = groupMaxRank.get(root) + 1;
                result[cell[0]][cell[1]] = rank;
                rowMaxRank[cell[0]] = Math.max(rowMaxRank[cell[0]], rank);
                colMaxRank[cell[1]] = Math.max(colMaxRank[cell[1]], rank);
            }
        }
        return result;
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
