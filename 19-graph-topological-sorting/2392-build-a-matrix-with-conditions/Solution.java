import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 2392. Build a Matrix With Conditions
 * Approach: Row and column placement are independent constraints, so
 * topologically sort each condition list separately (Kahn's algorithm)
 * to get a row order and a column order for the numbers 1..k; then place
 * each number at the intersection of its row-order and column-order rank.
 * Time: O(k + rowConditions + colConditions) | Space: O(k^2)
 */
class Solution {
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        int[] rowOrder = topoSort(k, rowConditions);
        if (rowOrder == null) return new int[0][0];
        int[] colOrder = topoSort(k, colConditions);
        if (colOrder == null) return new int[0][0];

        int[] rowPos = new int[k + 1], colPos = new int[k + 1];
        for (int i = 0; i < k; i++) rowPos[rowOrder[i]] = i;
        for (int i = 0; i < k; i++) colPos[colOrder[i]] = i;

        int[][] result = new int[k][k];
        for (int num = 1; num <= k; num++) {
            result[rowPos[num]][colPos[num]] = num;
        }
        return result;
    }

    private int[] topoSort(int k, int[][] conditions) {
        List<List<Integer>> adj = new ArrayList<>();
        int[] indegree = new int[k + 1];
        for (int i = 0; i <= k; i++) adj.add(new ArrayList<>());
        for (int[] c : conditions) {
            adj.get(c[0]).add(c[1]);
            indegree[c[1]]++;
        }
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= k; i++) {
            if (indegree[i] == 0) queue.add(i);
        }
        int[] order = new int[k];
        int idx = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            order[idx++] = node;
            for (int next : adj.get(node)) {
                if (--indegree[next] == 0) queue.add(next);
            }
        }
        return idx == k ? order : null;
    }
}
