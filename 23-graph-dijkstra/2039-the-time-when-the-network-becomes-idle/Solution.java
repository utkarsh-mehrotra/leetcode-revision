import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 2039. The Time When the Network Becomes Idle
 * Approach: BFS from server 0 (all edges weight 1) gives each server's
 * one-way distance; round trip time is double that. A server keeps
 * resending every `patience` minutes until its first reply arrives at
 * time roundTrip -- its last resend is the largest multiple of patience
 * strictly before roundTrip, and it goes idle one minute after that
 * resend's own reply comes back. The network is idle once every server
 * is.
 * Time: O(V + E) | Space: O(V + E)
 */
class Solution {
    public int networkBecomesIdle(int[][] edges, int[] patience) {
        int n = patience.length;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        int[] dist = new int[n];
        java.util.Arrays.fill(dist, -1);
        dist[0] = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int next : adj.get(node)) {
                if (dist[next] == -1) {
                    dist[next] = dist[node] + 1;
                    queue.add(next);
                }
            }
        }

        int answer = 0;
        for (int i = 1; i < n; i++) {
            int roundTrip = 2 * dist[i];
            int lastSend = ((roundTrip - 1) / patience[i]) * patience[i];
            int idleAt = lastSend + roundTrip + 1;
            answer = Math.max(answer, idleAt);
        }
        return answer;
    }
}
