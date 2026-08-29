import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1782. Count Pairs of Nodes
 * Approach: The number of edges incident to {a, b} is degree[a] +
 * degree[b] minus 1 if a direct edge connects them (it would otherwise
 * be double-counted). For each query, sort nodes by degree and two-
 * pointer count pairs whose degree sum exceeds the query, then subtract
 * off the pairs that were over-counted because they share a direct edge
 * whose adjustment flips them from "counts" to "doesn't count" (or vice
 * versa) right at the threshold.
 * Time: O((n+m) log n + m + q log n) | Space: O(n + m)
 */
class Solution {
    public int[] countPairs(int n, int[][] edges, int[] queries) {
        int[] degree = new int[n + 1];
        Map<Long, Integer> edgeCount = new HashMap<>();
        for (int[] e : edges) {
            degree[e[0]]++;
            degree[e[1]]++;
            long key = pairKey(e[0], e[1]);
            edgeCount.merge(key, 1, Integer::sum);
        }

        int[] sortedDegree = new int[n + 1];
        System.arraycopy(degree, 1, sortedDegree, 1, n);
        java.util.Arrays.sort(sortedDegree, 1, n + 1);

        int[] answers = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int target = queries[q];
            long count = 0;
            int lo = 1, hi = n;
            while (lo < hi) {
                if (sortedDegree[lo] + sortedDegree[hi] > target) {
                    count += hi - lo;
                    hi--;
                } else {
                    lo++;
                }
            }
            // Correct for direct edges: the pair-sum check above used raw
            // degrees, but a direct edge reduces the true incident count by 1.
            for (Map.Entry<Long, Integer> entry : edgeCount.entrySet()) {
                int u = (int) (entry.getKey() >> 32);
                int v = (int) (long) entry.getKey();
                int multiplicity = entry.getValue();
                boolean wasCounted = degree[u] + degree[v] > target;
                boolean trulyCounted = degree[u] + degree[v] - multiplicity > target;
                if (wasCounted && !trulyCounted) count--;
                else if (!wasCounted && trulyCounted) count++;
            }
            answers[q] = (int) count;
        }
        return answers;
    }

    private long pairKey(int a, int b) {
        int lo = Math.min(a, b), hi = Math.max(a, b);
        return ((long) lo << 32) | (hi & 0xffffffffL);
    }
}
