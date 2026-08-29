import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 133. Clone Graph
 * Approach: DFS from the given node, using a HashMap from original node ->
 * clone to (a) avoid infinite recursion on cycles and (b) ensure every
 * original node maps to exactly one clone regardless of how many times
 * it's reached. Each node's clone is created (with an empty neighbor list)
 * BEFORE recursing into its neighbors, so a cycle back to it resolves via
 * the map instead of re-cloning.
 * Time: O(V+E) | Space: O(V)
 */
class Solution {
    private final Map<Node, Node> cloned = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        if (cloned.containsKey(node)) return cloned.get(node);

        Node copy = new Node(node.val);
        cloned.put(node, copy);
        for (Node neighbor : node.neighbors) {
            copy.neighbors.add(cloneGraph(neighbor));
        }
        return copy;
    }
}
