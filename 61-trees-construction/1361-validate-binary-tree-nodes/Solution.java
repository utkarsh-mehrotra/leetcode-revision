import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode 1361. Validate Binary Tree Nodes
 * Approach: A valid tree has exactly one node with indegree 0 (the
 * root) and every other node with indegree exactly 1 -- any node with
 * indegree > 1 has two parents, which immediately disqualifies the
 * structure. Indegree alone doesn't rule out disjoint cycles elsewhere,
 * so a DFS/BFS from the single root must additionally reach all n nodes
 * exactly once.
 * Time: O(n) | Space: O(n)
 */
class Solution {
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) {
            if (leftChild[i] != -1) indegree[leftChild[i]]++;
            if (rightChild[i] != -1) indegree[rightChild[i]]++;
        }
        int root = -1;
        for (int i = 0; i < n; i++) {
            if (indegree[i] > 1) return false;
            if (indegree[i] == 0) {
                if (root != -1) return false;
                root = i;
            }
        }
        if (root == -1) return false;

        boolean[] visited = new boolean[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(root);
        visited[root] = true;
        int count = 1;
        while (!stack.isEmpty()) {
            int node = stack.pop();
            for (int child : new int[]{leftChild[node], rightChild[node]}) {
                if (child == -1) continue;
                if (visited[child]) return false;
                visited[child] = true;
                count++;
                stack.push(child);
            }
        }
        return count == n;
    }
}
