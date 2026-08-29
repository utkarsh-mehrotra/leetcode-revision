import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 2508. Add Edges to Make Degrees of All Nodes Even
 * Approach: The degree sum is always even, so the odd-degree node count
 * is always even too -- 0, 2, or 4 (more than 4 can never be fixed with
 * only 2 new edges). 0 odd nodes: already done. 2 odd nodes (a,b): a
 * single new edge a-b fixes both, unless that edge already exists, in
 * which case route through any third node c not yet adjacent to either
 * (two new edges). 4 odd nodes: try all 3 ways to pair them into two
 * edges, succeeding if some pairing's edges are both currently absent.
 * Time: O(n^2) | Space: O(n^2)
 */
class Solution {
    public boolean isPossible(int n, List<List<Integer>> edges) {
        boolean[][] adj = new boolean[n + 1][n + 1];
        int[] degree = new int[n + 1];
        for (List<Integer> e : edges) {
            int u = e.get(0), v = e.get(1);
            adj[u][v] = true;
            adj[v][u] = true;
            degree[u]++;
            degree[v]++;
        }

        List<Integer> odd = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (degree[i] % 2 != 0) odd.add(i);
        }

        if (odd.isEmpty()) return true;
        if (odd.size() == 2) {
            int a = odd.get(0), b = odd.get(1);
            if (!adj[a][b]) return true;
            for (int c = 1; c <= n; c++) {
                if (c != a && c != b && !adj[a][c] && !adj[c][b]) return true;
            }
            return false;
        }
        if (odd.size() == 4) {
            int a = odd.get(0), b = odd.get(1), c = odd.get(2), d = odd.get(3);
            if (!adj[a][b] && !adj[c][d]) return true;
            if (!adj[a][c] && !adj[b][d]) return true;
            if (!adj[a][d] && !adj[b][c]) return true;
            return false;
        }
        return false;
    }
}
