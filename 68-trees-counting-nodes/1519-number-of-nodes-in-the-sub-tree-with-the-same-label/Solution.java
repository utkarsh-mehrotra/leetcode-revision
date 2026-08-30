import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 1519. Number of Nodes in the Sub-Tree With the Same Label
 * Approach: Build an undirected adjacency list from the edges (the tree
 * is given generically, not as a TreeNode). Post-order DFS from node 0:
 * each call returns a 26-length letter-frequency count covering its own
 * subtree, built by summing its children's counts and adding its own
 * label. The answer for a node is simply that count at its own label.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        int[] ans = new int[n];
        boolean[] visited = new boolean[n];
        dfs(0, graph, labels, ans, visited);
        return ans;
    }

    private int[] dfs(int node, List<List<Integer>> graph, String labels, int[] ans, boolean[] visited) {
        visited[node] = true;
        int[] count = new int[26];
        count[labels.charAt(node) - 'a']++;
        for (int neighbor : graph.get(node)) {
            if (visited[neighbor]) continue;
            int[] childCount = dfs(neighbor, graph, labels, ans, visited);
            for (int i = 0; i < 26; i++) count[i] += childCount[i];
        }
        ans[node] = count[labels.charAt(node) - 'a'];
        return count;
    }
}
