import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 3015. Count the Number of Houses at a Certain Distance I
 * Approach: The graph is a cycle of n houses plus one extra chord (x,y).
 * BFS from every house gives that house's exact distance to all others
 * in O(n); running this from every source (only counting each unordered
 * pair once, when the source's index is smaller) tallies every pair's
 * distance directly. A closed-form O(n) formula exists for this specific
 * cycle-plus-chord structure, but the O(n^2) BFS approach here is simpler
 * to verify correct and still reasonably efficient.
 * Time: O(n^2) | Space: O(n)
 */
class Solution {
    public int[] countOfPairs(int n, int x, int y) {
        x--;
        y--;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < n; i++) {
            int next = (i + 1) % n;
            adj.get(i).add(next);
            adj.get(next).add(i);
        }
        if (x != y) {
            adj.get(x).add(y);
            adj.get(y).add(x);
        }

        int[] result = new int[n];
        for (int src = 0; src < n; src++) {
            int[] dist = new int[n];
            Arrays.fill(dist, -1);
            dist[src] = 0;
            Deque<Integer> queue = new ArrayDeque<>();
            queue.add(src);
            while (!queue.isEmpty()) {
                int node = queue.poll();
                for (int next : adj.get(node)) {
                    if (dist[next] == -1) {
                        dist[next] = dist[node] + 1;
                        queue.add(next);
                    }
                }
            }
            for (int j = src + 1; j < n; j++) {
                result[dist[j] - 1]++;
            }
        }
        return result;
    }
}
