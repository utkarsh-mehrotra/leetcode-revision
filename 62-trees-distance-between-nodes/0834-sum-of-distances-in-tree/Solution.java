import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 834. Sum of Distances in Tree
 * Approach: Two-pass "rerooting" technique. A post-order DFS rooted at
 * node 0 computes each subtree's size and ans[0] (the true answer for
 * node 0, built from each child's answer plus its subtree size). A
 * second pre-order DFS then "rolls" the answer from parent to child in
 * O(1): moving the root from parent to child pulls it count[child]
 * closer to every node inside child's subtree, and (n - count[child])
 * farther from every node outside it.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    private List<List<Integer>> graph;
    private int[] count;
    private int[] ans;
    private int n;

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        this.n = n;
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        count = new int[n];
        ans = new int[n];
        postOrder(0, -1);
        preOrder(0, -1);
        return ans;
    }

    private void postOrder(int node, int parent) {
        count[node] = 1;
        for (int child : graph.get(node)) {
            if (child == parent) continue;
            postOrder(child, node);
            count[node] += count[child];
            ans[node] += ans[child] + count[child];
        }
    }

    private void preOrder(int node, int parent) {
        for (int child : graph.get(node)) {
            if (child == parent) continue;
            ans[child] = ans[node] - count[child] + (n - count[child]);
            preOrder(child, node);
        }
    }
}
