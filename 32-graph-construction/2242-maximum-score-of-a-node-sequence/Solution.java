import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 2242. Maximum Score of a Node Sequence
 * Approach: A valid 4-node sequence a-b-c-d is fully determined by its
 * middle edge (b,c) plus one extra neighbor of b and one extra neighbor
 * of c (all four nodes distinct). Precompute, for every node, its top-3
 * highest-score neighbors -- keeping 3 (not just 1) guarantees that even
 * if the best neighbor collides with the other endpoint of the middle
 * edge or with the wing chosen on the other side, a usable alternative
 * survives. Then for each edge (u,v), try every combination of a wing
 * from u's top-3 and a wing from v's top-3, skipping any that reuse a
 * node, and keep the best total.
 * Time: O(V + E) | Space: O(V)
 */
class Solution {
    public int maximumScore(int[] scores, int[][] edges) {
        int n = scores.length;
        List<List<Integer>> topNeighbors = new ArrayList<>();
        for (int i = 0; i < n; i++) topNeighbors.add(new ArrayList<>());

        for (int[] e : edges) {
            addTopNeighbor(topNeighbors.get(e[0]), e[1], scores);
            addTopNeighbor(topNeighbors.get(e[1]), e[0], scores);
        }

        int best = -1;
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            for (int wingU : topNeighbors.get(u)) {
                if (wingU == v) continue;
                for (int wingV : topNeighbors.get(v)) {
                    if (wingV == u || wingV == wingU) continue;
                    int total = scores[u] + scores[v] + scores[wingU] + scores[wingV];
                    best = Math.max(best, total);
                }
            }
        }
        return best;
    }

    // Keeps at most the 3 highest-scoring neighbors, sorted descending by score.
    private void addTopNeighbor(List<Integer> neighbors, int candidate, int[] scores) {
        int i = neighbors.size();
        neighbors.add(candidate);
        while (i > 0 && scores[neighbors.get(i - 1)] < scores[neighbors.get(i)]) {
            int tmp = neighbors.get(i - 1);
            neighbors.set(i - 1, neighbors.get(i));
            neighbors.set(i, tmp);
            i--;
        }
        if (neighbors.size() > 3) neighbors.remove(neighbors.size() - 1);
    }
}
