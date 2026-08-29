import java.util.Arrays;

/**
 * LeetCode 2359. Find Closest Node to Given Two Nodes
 * Approach: Each node has at most one outgoing edge, so its reachable
 * set is a simple chain -- walk it directly to fill in distances (no
 * queue needed). A node qualifies only if BOTH walks reach it; among
 * those, minimize the WORSE of the two distances (smallest index breaks ties).
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length;
        int[] dist1 = walkDistances(edges, node1, n);
        int[] dist2 = walkDistances(edges, node2, n);

        int best = -1, bestMax = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (dist1[i] == -1 || dist2[i] == -1) continue;
            int worse = Math.max(dist1[i], dist2[i]);
            if (worse < bestMax) {
                bestMax = worse;
                best = i;
            }
        }
        return best;
    }

    private int[] walkDistances(int[] edges, int src, int n) {
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[src] = 0;
        int cur = src;
        while (edges[cur] != -1 && dist[edges[cur]] == -1) {
            dist[edges[cur]] = dist[cur] + 1;
            cur = edges[cur];
        }
        return dist;
    }
}
