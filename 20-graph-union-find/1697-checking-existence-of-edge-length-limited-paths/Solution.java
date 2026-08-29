import java.util.Arrays;

/**
 * LeetCode 1697. Checking Existence of Edge Length Limited Paths
 * Approach: Offline Union-Find -- sort edges by weight and queries by
 * limit, then sweep both in increasing order, unioning every edge
 * lighter than the current query's limit before answering it. Since
 * both lists only move forward, each edge/query is processed once.
 * Time: O((E+Q) log(E+Q)) | Space: O(n+E+Q)
 */
class Solution {
    private int[] parent;

    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        Integer[] edgeOrder = new Integer[edgeList.length];
        for (int i = 0; i < edgeList.length; i++) edgeOrder[i] = i;
        Arrays.sort(edgeOrder, (a, b) -> edgeList[a][2] - edgeList[b][2]);

        Integer[] queryOrder = new Integer[queries.length];
        for (int i = 0; i < queries.length; i++) queryOrder[i] = i;
        Arrays.sort(queryOrder, (a, b) -> queries[a][2] - queries[b][2]);

        boolean[] answers = new boolean[queries.length];
        int edgeIdx = 0;
        for (int qi : queryOrder) {
            int limit = queries[qi][2];
            while (edgeIdx < edgeList.length && edgeList[edgeOrder[edgeIdx]][2] < limit) {
                int[] edge = edgeList[edgeOrder[edgeIdx]];
                union(edge[0], edge[1]);
                edgeIdx++;
            }
            answers[qi] = find(queries[qi][0]) == find(queries[qi][1]);
        }
        return answers;
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
        if (ra != rb) parent[ra] = rb;
    }
}
