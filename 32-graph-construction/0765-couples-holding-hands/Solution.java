/**
 * LeetCode 765. Couples Holding Hands
 * Approach: Construct a graph where each couple is a node (person p's
 * couple id is p/2), and every adjacent seat PAIR unions the couple ids
 * of whoever is sitting there. A seat pair already holding its own couple
 * unions a node with itself (no-op). Once every seat pair has been
 * unioned, each connected component of size k represents a cluster of k
 * couples that are tangled together and need exactly k-1 swaps to
 * untangle (a cycle-swap argument: resolving one couple in the component
 * fixes one couple and shrinks the remaining tangle by one). Summing
 * (component size - 1) over all components gives the minimum swap count.
 * Time: O(n * alpha(n)) | Space: O(n)
 */
class Solution {
    private int[] parent;

    public int minSwapsCouples(int[] row) {
        int n = row.length;
        int couples = n / 2;
        parent = new int[couples];
        for (int i = 0; i < couples; i++) parent[i] = i;

        for (int seat = 0; seat < n; seat += 2) {
            union(row[seat] / 2, row[seat + 1] / 2);
        }

        int[] componentSize = new int[couples];
        for (int i = 0; i < couples; i++) componentSize[find(i)]++;

        int swaps = 0;
        for (int i = 0; i < couples; i++) {
            if (find(i) == i) swaps += componentSize[i] - 1;
        }
        return swaps;
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
