import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 787. Cheapest Flights Within K Stops
 * Approach: Top-down memoized recursion over (node, stopsLeft) -- the
 * cheapest way to reach dst from node using at most stopsLeft further
 * edges, trying every outgoing flight from node.
 * Time: O(V * K * E/V) = O(K * E) | Space: O(V * K)
 */
class Solution {
    private List<int[]>[] adj; // adj[u] = list of {v, price}
    private int dst;
    private Integer[][] dp;
    private static final int INF = Integer.MAX_VALUE / 2;

    @SuppressWarnings("unchecked")
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        this.dst = dst;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] f : flights) adj[f[0]].add(new int[]{f[1], f[2]});
        dp = new Integer[n][k + 2];
        int result = minCost(src, k + 1);
        return result >= INF ? -1 : result;
    }

    private int minCost(int node, int stopsLeft) {
        if (node == dst) return 0;
        if (stopsLeft == 0) return INF;
        if (dp[node][stopsLeft] != null) return dp[node][stopsLeft];
        int best = INF;
        for (int[] edge : adj[node]) {
            int next = edge[0], price = edge[1];
            int candidate = price + minCost(next, stopsLeft - 1);
            best = Math.min(best, candidate);
        }
        dp[node][stopsLeft] = best;
        return best;
    }
}
