import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 1192. Critical Connections in a Network
 * Approach: Tarjan's bridge-finding algorithm. DFS the graph tracking each
 * node's discovery time (disc) and low-link value (low = the earliest
 * discovery time reachable from this node's subtree via at most one back
 * edge). An edge (parent, node) is a bridge exactly when low[node] >
 * disc[parent] -- meaning node's subtree has no back edge reaching parent
 * or higher, so removing that edge disconnects it. Implemented iteratively
 * with an explicit stack (node, parent, next-neighbor-index) since n can
 * reach 10^5 and a recursive DFS would risk a stack overflow.
 * Time: O(V+E) | Space: O(V+E)
 */
class Solution {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (List<Integer> edge : connections) {
            graph.get(edge.get(0)).add(edge.get(1));
            graph.get(edge.get(1)).add(edge.get(0));
        }

        int[] disc = new int[n];
        int[] low = new int[n];
        Arrays.fill(disc, -1);
        int[] timer = {0};
        List<List<Integer>> bridges = new ArrayList<>();

        for (int start = 0; start < n; start++) {
            if (disc[start] != -1) continue;
            dfsIterative(start, graph, disc, low, timer, bridges);
        }
        return bridges;
    }

    private void dfsIterative(int start, List<List<Integer>> graph, int[] disc, int[] low,
                               int[] timer, List<List<Integer>> bridges) {
        // stack frame: {node, parent, nextNeighborIndex}
        Deque<int[]> stack = new ArrayDeque<>();
        disc[start] = low[start] = timer[0]++;
        stack.push(new int[]{start, -1, 0});

        while (!stack.isEmpty()) {
            int[] frame = stack.peek();
            int node = frame[0], parent = frame[1];

            if (frame[2] < graph.get(node).size()) {
                int next = graph.get(node).get(frame[2]++);
                if (next == parent) continue; // skip the edge back to the immediate parent
                if (disc[next] == -1) {
                    disc[next] = low[next] = timer[0]++;
                    stack.push(new int[]{next, node, 0});
                } else {
                    low[node] = Math.min(low[node], disc[next]); // back edge
                }
            } else {
                stack.pop();
                if (parent != -1) {
                    low[parent] = Math.min(low[parent], low[node]);
                    if (low[node] > disc[parent]) {
                        bridges.add(Arrays.asList(parent, node));
                    }
                }
            }
        }
    }
}
