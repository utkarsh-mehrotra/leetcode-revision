import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * LeetCode 1514. Path with Maximum Probability
 * Approach: Dijkstra with a max-heap instead of a min-heap -- probabilities
 * compose by multiplication (which preserves the "combining a finalized
 * best value with an edge weight can only make things worse" property
 * Dijkstra relies on), so the algorithm carries over directly by always
 * expanding the currently-highest-probability unfinalized node.
 * Time: O(E log V) | Space: O(V + E)
 */
class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<List<double[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0], b = edges[i][1];
            adj.get(a).add(new double[]{b, succProb[i]});
            adj.get(b).add(new double[]{a, succProb[i]});
        }

        double[] prob = new double[n];
        prob[start] = 1.0;
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(b[1], a[1]));
        pq.add(new double[]{start, 1.0});

        while (!pq.isEmpty()) {
            double[] cur = pq.poll();
            int node = (int) cur[0];
            double p = cur[1];
            if (p < prob[node]) continue;
            if (node == end) return p;
            for (double[] edge : adj.get(node)) {
                int next = (int) edge[0];
                double candidate = p * edge[1];
                if (candidate > prob[next]) {
                    prob[next] = candidate;
                    pq.add(new double[]{next, candidate});
                }
            }
        }
        return prob[end];
    }
}
