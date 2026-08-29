import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 802. Find Eventual Safe States
 * Approach: A node is "safe" iff every path from it eventually reaches a
 * terminal node without looping. DFS with 3-way coloring (unvisited / in
 * -progress / safe) detects this directly: hitting an in-progress node
 * means a cycle, making everything on the current path unsafe.
 * Time: O(V + E) | Space: O(V + E)
 */
class Solution {
    private static final int UNVISITED = 0, IN_PROGRESS = 1, SAFE = 2, UNSAFE = 3;
    private int[] state;
    private int[][] graph;

    public List<Integer> eventualSafeNodes(int[][] graph) {
        this.graph = graph;
        int n = graph.length;
        state = new int[n];
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (isSafe(i)) result.add(i);
        }
        return result;
    }

    private boolean isSafe(int node) {
        if (state[node] == SAFE) return true;
        if (state[node] == UNSAFE || state[node] == IN_PROGRESS) return false;
        state[node] = IN_PROGRESS;
        for (int next : graph[node]) {
            if (!isSafe(next)) {
                state[node] = UNSAFE;
                return false;
            }
        }
        state[node] = SAFE;
        return true;
    }
}
