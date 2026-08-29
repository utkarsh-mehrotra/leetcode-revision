import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LeetCode 2045. Second Minimum Time to Reach Destination
 * Approach: BFS tracking both the shortest AND second-shortest DISTINCT
 * path lengths (in edge count) to every node -- a node only needs a
 * second visit if the new distance differs from the one already
 * recorded. Once the second-shortest edge count to node n is known,
 * simulate walking that many edges while respecting the alternating
 * green/red traffic signal (waiting out any red light before departing).
 * Time: O(V + E) | Space: O(V + E)
 */
class Solution {
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        int[] dist1 = new int[n + 1], dist2 = new int[n + 1];
        java.util.Arrays.fill(dist1, -1);
        java.util.Arrays.fill(dist2, -1);
        dist1[1] = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{1, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int node = cur[0], d = cur[1];
            for (int next : adj.get(node)) {
                int nd = d + 1;
                if (dist1[next] == -1) {
                    dist1[next] = nd;
                    queue.add(new int[]{next, nd});
                } else if (dist1[next] != nd && dist2[next] == -1) {
                    dist2[next] = nd;
                    queue.add(new int[]{next, nd});
                }
            }
        }

        long curTime = 0;
        for (int step = 0; step < dist2[n]; step++) {
            long cycle = curTime / change;
            if (cycle % 2 == 1) {
                curTime = (cycle + 1) * change; // wait out the red light
            }
            curTime += time;
        }
        return (int) curTime;
    }
}
