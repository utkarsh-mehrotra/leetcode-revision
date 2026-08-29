import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 2876. Count Visited Nodes in a Directed Graph
 * Approach: Each node has exactly one outgoing edge. Walk from every
 * unresolved node, recording the path, until hitting a node that's
 * either mid-walk (closes a NEW cycle -- every node in that cycle gets
 * the cycle's length, and nodes before it count up from there) or
 * already fully resolved (the whole path just chains onto that node's
 * known answer). Every node is walked at most once overall.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public int[] countVisitedNodes(List<Integer> edges) {
        int n = edges.size();
        int[] result = new int[n];
        int[] state = new int[n]; // 0 = unvisited, 1 = in current walk, 2 = resolved

        for (int i = 0; i < n; i++) {
            if (state[i] != 0) continue;
            List<Integer> path = new ArrayList<>();
            Map<Integer, Integer> indexInPath = new HashMap<>();
            int cur = i;
            while (state[cur] == 0) {
                state[cur] = 1;
                indexInPath.put(cur, path.size());
                path.add(cur);
                cur = edges.get(cur);
            }

            if (state[cur] == 1) {
                int idx = indexInPath.get(cur);
                int cycleLen = path.size() - idx;
                for (int k = idx; k < path.size(); k++) {
                    result[path.get(k)] = cycleLen;
                    state[path.get(k)] = 2;
                }
                int prevResult = cycleLen;
                for (int k = idx - 1; k >= 0; k--) {
                    prevResult++;
                    result[path.get(k)] = prevResult;
                    state[path.get(k)] = 2;
                }
            } else {
                int prevResult = result[cur];
                for (int k = path.size() - 1; k >= 0; k--) {
                    prevResult++;
                    result[path.get(k)] = prevResult;
                    state[path.get(k)] = 2;
                }
            }
        }
        return result;
    }
}
