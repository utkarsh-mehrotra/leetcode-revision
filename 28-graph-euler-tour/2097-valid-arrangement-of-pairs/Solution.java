import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * LeetCode 2097. Valid Arrangement of Pairs
 * Approach: Each pair is a directed edge start_i -> end_i; a valid
 * arrangement is exactly an Eulerian path through all of them. The start
 * node of that path is the one node whose out-degree exceeds its in-degree
 * by 1 (guaranteed unique if it exists); otherwise the graph has an Euler
 * circuit and any node with an edge works. Hierholzer's algorithm (run
 * iteratively to avoid recursion depth issues on up to 15000 edges) then
 * produces the node sequence, which is converted back into consecutive
 * pairs.
 * Time: O(E) | Space: O(E)
 */
class Solution {
    public int[][] validArrangement(int[][] pairs) {
        Map<Integer, Deque<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> outDegree = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();

        for (int[] pair : pairs) {
            graph.computeIfAbsent(pair[0], k -> new ArrayDeque<>()).add(pair[1]);
            outDegree.merge(pair[0], 1, Integer::sum);
            inDegree.merge(pair[1], 1, Integer::sum);
        }

        int start = pairs[0][0];
        for (int node : outDegree.keySet()) {
            if (outDegree.get(node) - inDegree.getOrDefault(node, 0) == 1) {
                start = node;
                break;
            }
        }

        LinkedList<Integer> path = new LinkedList<>();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            int node = stack.peek();
            Deque<Integer> nextStops = graph.get(node);
            if (nextStops == null || nextStops.isEmpty()) {
                path.addFirst(stack.pop());
            } else {
                stack.push(nextStops.poll());
            }
        }

        int[][] result = new int[path.size() - 1][2];
        int prev = path.get(0);
        int idx = 0;
        for (int node : path.subList(1, path.size())) {
            result[idx++] = new int[]{prev, node};
            prev = node;
        }
        return result;
    }
}
