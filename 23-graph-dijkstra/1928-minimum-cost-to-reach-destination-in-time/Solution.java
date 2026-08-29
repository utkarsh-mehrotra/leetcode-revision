import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * LeetCode 1928. Minimum Cost to Reach Destination in Time
 * Approach: Dijkstra ordered by cost (not time) -- since we want the
 * cheapest arrival within a time budget, expand states in increasing
 * cost order and stop at the first time city n-1 is popped. A node is
 * only worth re-expanding via a new state if that state's travel time
 * improves the best time seen so far for it (a worse time AND worse cost
 * is strictly dominated and pruned).
 * Time: O(E log E) | Space: O(V + E)
 */
class Solution {
    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(new int[]{e[1], e[2]});
            adj.get(e[1]).add(new int[]{e[0], e[2]});
        }

        int[] minTime = new int[n];
        Arrays.fill(minTime, Integer.MAX_VALUE);
        minTime[0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]); // {node, time, cost}
        pq.add(new int[]{0, 0, passingFees[0]});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0], time = cur[1], cost = cur[2];
            if (node == n - 1) return cost;
            if (time > minTime[node]) continue;
            for (int[] edge : adj.get(node)) {
                int next = edge[0], w = edge[1];
                int newTime = time + w;
                if (newTime <= maxTime && newTime < minTime[next]) {
                    minTime[next] = newTime;
                    pq.add(new int[]{next, newTime, cost + passingFees[next]});
                }
            }
        }
        return -1;
    }
}
