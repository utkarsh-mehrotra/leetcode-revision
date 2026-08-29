import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 753. Cracking the Safe
 * Approach: This is a De Bruijn sequence problem in disguise. Build the
 * implicit De Bruijn graph where nodes are all length-(n-1) strings over
 * the k-digit alphabet and each node has k outgoing edges (append digit c),
 * landing on the node formed by dropping the first character. A password
 * of length n corresponds to exactly one EDGE, so a string that contains
 * every password as a substring exactly once corresponds to an Eulerian
 * circuit over this graph -- which is guaranteed to exist since every node
 * has equal in/out degree k. Hierholzer's algorithm (post-order DFS,
 * appending the edge label after fully exploring it) builds that circuit;
 * reading edge labels in reverse post-order gives the shortest safe-cracking
 * string. Starting node is the all-zero string.
 * Time: O(k^n) | Space: O(k^n)
 */
class Solution {
    private final Set<String> usedEdges = new HashSet<>();
    private final StringBuilder result = new StringBuilder();
    private int k;

    public String crackSafe(int n, int k) {
        this.k = k;
        StringBuilder start = new StringBuilder();
        for (int i = 0; i < n - 1; i++) start.append('0');
        String startNode = start.toString();

        dfs(startNode, n);
        result.append(startNode);
        return result.toString();
    }

    // Explore every unused outgoing edge from `node`, appending the digit
    // AFTER recursing (post-order) so the circuit is built in reverse.
    private void dfs(String node, int n) {
        for (char digit = '0'; digit < '0' + k; digit++) {
            String edge = node + digit;
            if (!usedEdges.contains(edge)) {
                usedEdges.add(edge);
                dfs(edge.substring(1), n);
                result.append(digit);
            }
        }
    }
}
