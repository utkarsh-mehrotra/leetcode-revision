import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * LeetCode 2662. Minimum Cost of a Path With Special Roads
 * Approach: Only "interesting" points matter -- start, target, and every
 * special road's two endpoints. Between any two interesting points a
 * free-form Manhattan-distance move is always available; special roads
 * add extra directed edges. Dijkstra over this small point set finds the
 * cheapest route.
 * Time: O(m^2 log m) | Space: O(m^2)
 */
class Solution {
    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        List<int[]> points = new ArrayList<>();
        points.add(start);
        points.add(target);
        for (int[] r : specialRoads) {
            points.add(new int[]{r[0], r[1]});
            points.add(new int[]{r[2], r[3]});
        }
        int m = points.size();

        long[] dist = new long[m];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[0] = 0;
        boolean[] done = new boolean[m];
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        pq.add(new long[]{0, 0});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int idx = (int) cur[0];
            if (done[idx]) continue;
            done[idx] = true;
            if (idx == 1) return (int) dist[1];

            int[] p = points.get(idx);
            for (int j = 0; j < m; j++) {
                if (j == idx) continue;
                int[] q = points.get(j);
                long w = Math.abs(p[0] - q[0]) + Math.abs(p[1] - q[1]);
                if (dist[idx] + w < dist[j]) {
                    dist[j] = dist[idx] + w;
                    pq.add(new long[]{j, dist[j]});
                }
            }
            for (int[] r : specialRoads) {
                if (p[0] == r[0] && p[1] == r[1]) {
                    for (int j = 0; j < m; j++) {
                        int[] q = points.get(j);
                        if (q[0] == r[2] && q[1] == r[3]) {
                            long w = r[4];
                            if (dist[idx] + w < dist[j]) {
                                dist[j] = dist[idx] + w;
                                pq.add(new long[]{j, dist[j]});
                            }
                        }
                    }
                }
            }
        }
        return (int) dist[1];
    }
}
