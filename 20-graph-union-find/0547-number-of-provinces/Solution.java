/**
 * LeetCode 547. Number of Provinces
 * Approach: Union-Find with path compression and union by rank -- union
 * every directly-connected pair of cities, then the number of distinct
 * roots left is the number of provinces.
 * Time: O(n^2 * alpha(n)) | Space: O(n)
 */
class Solution {
    private int[] parent, rank_;

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        parent = new int[n];
        rank_ = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) union(i, j);
            }
        }

        int provinces = 0;
        for (int i = 0; i < n; i++) {
            if (find(i) == i) provinces++;
        }
        return provinces;
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
        if (rank_[ra] < rank_[rb]) { int t = ra; ra = rb; rb = t; }
        parent[rb] = ra;
        if (rank_[ra] == rank_[rb]) rank_[ra]++;
    }
}
