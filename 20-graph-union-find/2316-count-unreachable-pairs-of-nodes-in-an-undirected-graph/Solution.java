/**
 * LeetCode 2316. Count Unreachable Pairs of Nodes in an Undirected Graph
 * Approach: Union-Find to group nodes into components and tally each
 * component's size. Every pair within the same component IS reachable,
 * so unreachable pairs = total pairs C(n,2) minus the reachable pairs
 * (sum of C(size,2) over every component).
 * Time: O(n + edges * alpha(n)) | Space: O(n)
 */
class Solution {
    private int[] parent, size;

    public long countPairs(int n, int[][] edges) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        for (int[] e : edges) {
            union(e[0], e[1]);
        }

        long totalPairs = (long) n * (n - 1) / 2;
        long reachablePairs = 0;
        for (int i = 0; i < n; i++) {
            if (find(i) == i) {
                reachablePairs += (long) size[i] * (size[i] - 1) / 2;
            }
        }
        return totalPairs - reachablePairs;
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
        if (ra == rb) return;
        parent[ra] = rb;
        size[rb] += size[ra];
    }
}
