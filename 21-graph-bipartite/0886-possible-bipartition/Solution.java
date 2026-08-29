import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 886. Possible Bipartition
 * Approach: Build a dislike graph and BFS 2-color it per component --
 * two disliking people must always land in opposite groups; hitting a
 * disliked neighbor already the SAME color means no valid 2-way split exists.
 * Time: O(n + dislikes) | Space: O(n + dislikes)
 */
class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        for (int[] d : dislikes) {
            adj.get(d[0]).add(d[1]);
            adj.get(d[1]).add(d[0]);
        }

        int[] color = new int[n + 1];
        for (int start = 1; start <= n; start++) {
            if (color[start] != 0) continue;
            color[start] = 1;
            java.util.Deque<Integer> queue = new java.util.ArrayDeque<>();
            queue.add(start);
            while (!queue.isEmpty()) {
                int node = queue.poll();
                for (int next : adj.get(node)) {
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
