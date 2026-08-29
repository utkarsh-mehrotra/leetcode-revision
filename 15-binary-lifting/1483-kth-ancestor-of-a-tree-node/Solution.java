/**
 * LeetCode 1483. Kth Ancestor of a Tree Node
 * Approach: Binary lifting via top-down memoized recursion -- up(node, j)
 * is node's 2^j-th ancestor, built from up(up(node, j-1), j-1) (jump
 * halfway, then halfway again). A query for the k-th ancestor decomposes
 * k into its set bits, jumping by each corresponding power of two.
 * Time: O(n log n) preprocessing, O(log n) per query | Space: O(n log n)
 */
class TreeAncestor {
    private int[] parent;
    private int[][] dp; // dp[node][j] = 2^j-th ancestor, -1 if it doesn't exist
    private int maxJ;

    public TreeAncestor(int n, int[] parent) {
        this.parent = parent;
        this.maxJ = Math.max(1, 32 - Integer.numberOfLeadingZeros(Math.max(1, n)));
        this.dp = new int[n][maxJ + 1];
        for (int[] row : dp) java.util.Arrays.fill(row, -2); // -2 = uncomputed
    }

    public int getKthAncestor(int node, int k) {
        int current = node;
        for (int j = 0; j <= maxJ && current != -1; j++) {
            if ((k & (1 << j)) != 0) {
                current = up(current, j);
            }
        }
        return current;
    }

    private int up(int node, int j) {
        if (node == -1) return -1;
        if (j == 0) return parent[node];
        if (dp[node][j] != -2) return dp[node][j];
        int half = up(node, j - 1);
        int result = up(half, j - 1);
        dp[node][j] = result;
        return result;
    }
}
