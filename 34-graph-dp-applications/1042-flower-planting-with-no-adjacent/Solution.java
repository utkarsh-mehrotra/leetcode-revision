import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 1042. Flower Planting With No Adjacent
 * Approach: NOT a DP fit -- this is a graph-coloring problem with no
 * optimization objective (any valid coloring is accepted), so there's no
 * value function to memoize. Since every garden has at most 3 paths (the
 * problem's own constraint), 4 colors always suffice by a simple greedy
 * argument: process gardens in order and assign each the lowest-numbered
 * color (1-4) not already used by an already-colored neighbor -- at most
 * 3 neighbors can be colored before garden i, leaving at least one of the
 * 4 colors free.
 * Time: O(V+E) | Space: O(V+E)
 */
class Solution {
    public int[] gardenNoAdj(int n, int[][] paths) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] p : paths) {
            graph.get(p[0] - 1).add(p[1] - 1);
            graph.get(p[1] - 1).add(p[0] - 1);
        }

        int[] color = new int[n];
        for (int garden = 0; garden < n; garden++) {
            boolean[] used = new boolean[5];
            for (int neighbor : graph.get(garden)) {
                used[color[neighbor]] = true;
            }
            for (int c = 1; c <= 4; c++) {
                if (!used[c]) {
                    color[garden] = c;
                    break;
                }
            }
        }
        return color;
    }
}
