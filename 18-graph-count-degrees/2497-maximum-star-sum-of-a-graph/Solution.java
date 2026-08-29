import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 2497. Maximum Star Sum of a Graph
 * Approach: For each node as the star's center, only its positive-valued
 * neighbors are ever worth including (a negative neighbor only lowers
 * the sum), and among those, taking the top k largest maximizes it. Sort
 * each node's neighbor values descending and greedily take up to k
 * non-negative ones.
 * Time: O((n + edges) log maxDegree) | Space: O(n + edges)
 */
class Solution {
    public int maxStarSum(int[] vals, int[][] edges, int k) {
        int n = vals.length;
        List<List<Integer>> neighborVals = new ArrayList<>();
        for (int i = 0; i < n; i++) neighborVals.add(new ArrayList<>());
        for (int[] e : edges) {
            neighborVals.get(e[0]).add(vals[e[1]]);
            neighborVals.get(e[1]).add(vals[e[0]]);
        }

        int best = Integer.MIN_VALUE;
        for (int center = 0; center < n; center++) {
            List<Integer> neighbors = neighborVals.get(center);
            neighbors.sort((a, b) -> b - a);
            int sum = vals[center];
            for (int i = 0; i < Math.min(k, neighbors.size()); i++) {
                if (neighbors.get(i) <= 0) break;
                sum += neighbors.get(i);
            }
            best = Math.max(best, sum);
        }
        return best;
    }
}
