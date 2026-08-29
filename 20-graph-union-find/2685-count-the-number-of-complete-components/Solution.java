/**
 * LeetCode 2685. Count the Number of Complete Components
 * Approach: Union-Find tracking each component's node count and edge
 * count. A component with k nodes is complete iff it has exactly
 * k*(k-1)/2 edges (every possible pair connected); tally components
 * meeting that condition.
 * Time: O(n + edges * alpha(n)) | Space: O(n)
 */
class Solution {
    private int[] parent, nodeCount, edgeCount;

    public int countCompleteComponents(int n, int[][] edges) {
        parent = new int[n];
        nodeCount = new int[n];
        edgeCount = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            nodeCount[i] = 1;
        }
        for (int[] e : edges) {
            union(e[0], e[1]);
        }

        int complete = 0;
        for (int i = 0; i < n; i++) {
            if (find(i) == i) {
                long needed = (long) nodeCount[i] * (nodeCount[i] - 1) / 2;
                if (edgeCount[i] == needed) complete++;
            }
        }
        return complete;
    }

    private int find(int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    private void union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) {
            edgeCount[ra]++;
            return;
        }
        parent[ra] = rb;
        nodeCount[rb] += nodeCount[ra];
        edgeCount[rb] += edgeCount[ra] + 1;
    }
}
