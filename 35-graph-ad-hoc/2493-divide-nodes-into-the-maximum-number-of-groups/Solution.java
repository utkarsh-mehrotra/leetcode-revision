import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 2493. Divide Nodes Into the Maximum Number of Groups
 * Approach: A valid grouping is exactly a BFS leveling from some root,
 * which only exists without conflicts if every connected component is
 * bipartite -- checked with a single coloring BFS pass over the whole
 * graph (any odd cycle fails it, so return -1 immediately). For each
 * bipartite component, the best possible group count is its diameter + 1
 * (the longest shortest path, leveled from one of its endpoints). Finding
 * that endpoint uses the classic double-BFS "farthest node" trick: BFS
 * from any node to find a farthest node u, then BFS from u to find the
 * true max depth -- valid here because u is guaranteed to be an
 * eccentric (peripheral) vertex of the component. Summing each
 * component's best count gives the answer.
 * Time: O(V+E) | Space: O(V+E)
 */
class Solution {
    public int magnificentSets(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] e : edges) {
            graph.get(e[0] - 1).add(e[1] - 1);
            graph.get(e[1] - 1).add(e[0] - 1);
        }

        int[] color = new int[n];
        Arrays.fill(color, -1);
        int[] component = new int[n];
        Arrays.fill(component, -1);
        List<List<Integer>> components = new ArrayList<>();

        for (int start = 0; start < n; start++) {
            if (color[start] != -1) continue;
            List<Integer> nodes = new ArrayList<>();
            color[start] = 0;
            Deque<Integer> queue = new ArrayDeque<>();
            queue.add(start);
            int compId = components.size();
            component[start] = compId;
            while (!queue.isEmpty()) {
                int node = queue.poll();
                nodes.add(node);
                for (int next : graph.get(node)) {
                    if (color[next] == -1) {
                        color[next] = 1 - color[node];
                        component[next] = compId;
                        queue.add(next);
                    } else if (color[next] == color[node]) {
                        return -1; // odd cycle: not bipartite
                    }
                }
            }
            components.add(nodes);
        }

        int total = 0;
        for (List<Integer> nodes : components) {
            int arbitrary = nodes.get(0);
            int farthest = bfsFarthest(arbitrary, graph)[0];
            int maxDepth = bfsFarthest(farthest, graph)[1];
            total += maxDepth + 1;
        }
        return total;
    }

    // Returns {farthestNode, maxDistance} found via BFS from `start`.
    private int[] bfsFarthest(int start, List<List<Integer>> graph) {
        int[] dist = new int[graph.size()];
        Arrays.fill(dist, -1);
        dist[start] = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        int farthestNode = start, maxDist = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (dist[node] > maxDist) {
                maxDist = dist[node];
                farthestNode = node;
            }
            for (int next : graph.get(node)) {
                if (dist[next] == -1) {
                    dist[next] = dist[node] + 1;
                    queue.add(next);
                }
            }
        }
        return new int[]{farthestNode, maxDist};
    }
}
