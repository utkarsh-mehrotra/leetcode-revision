import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode 2192. All Ancestors of a Node in a Directed Acyclic Graph
 * Approach: Kahn's-algorithm topological order guarantees every
 * predecessor of a node is finalized before the node itself is popped;
 * propagate each node's full ancestor set (as a bitset, for fast bulk OR)
 * to its direct successors when relaxing them.
 * Time: O((V + E) * V / 64) | Space: O(V^2 / 64)
 */
class Solution {
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            indegree[e[1]]++;
        }

        BitSet[] ancestors = new BitSet[n];
        for (int i = 0; i < n; i++) ancestors[i] = new BitSet(n);

        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) queue.add(i);
        }
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int next : adj.get(node)) {
                ancestors[next].set(node);
                ancestors[next].or(ancestors[node]);
                if (--indegree[next] == 0) queue.add(next);
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>();
            for (int a = ancestors[i].nextSetBit(0); a >= 0; a = ancestors[i].nextSetBit(a + 1)) {
                list.add(a);
            }
            result.add(list);
        }
        return result;
    }
}
